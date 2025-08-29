package com.numbering.demo.NUM_PACKAGE;

import com.aspose.words.*;
import com.aspose.words.Font;
import com.numbering.demo.DTO.DocumentStyleDTO;
import com.numbering.demo.DTO.TabStopDTO;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NUMService {

    public Map<String,ListInfo> createDocumentStyles(List<DocumentStyleDTO> styles, Document doc) throws Exception {
        Map<String, ListInfo> listIds = new HashMap<>();
        try {

            StyleCollection docStyles = doc.getStyles();

            ListInfo  compactListID   = createListBullet(doc);
            ListInfo smallBulletID   = createNumberingListBulletSmall(doc);
            ListInfo spaceIndentID   = createListBulletIndent(doc);
            ListInfo numberingListID = createOutlineNumbering(doc);

            listIds.put("ListBullet", compactListID);
            listIds.put("ListbulletIndent",  spaceIndentID);
            listIds.put("ListBulletSmall", smallBulletID);
            listIds.put("OutlineNumbering", numberingListID);

            for (DocumentStyleDTO styleDTO : styles) {
                String styleName = styleDTO.getStyleId();
                Style existingStyle = docStyles.get(styleName);

                if (existingStyle != null) {
                    updateStyleProperites(styleDTO, existingStyle);
                } else {
                    createAndConfigureStyle(doc,styleDTO,listIds);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listIds;
    }

    public ListInfo createListBullet(Document document) throws Exception {
        // 1. Create bullet list
        com.aspose.words.List bulletList = document.getLists().add(ListTemplate.BULLET_DEFAULT);
        String[] bulletSymbols = {"\uF0B7", "\uF02D"};

        for (int i = 0; i < 9; i++) {
            ListLevel level = bulletList.getListLevels().get(i);
            level.getFont().setName("Symbol");
            level.setNumberFormat(bulletSymbols[i % 2]);

            double leftPt = 18 + (i * 18);
            double hangingPt = 18;

            level.setNumberPosition(hangingPt);
            level.setTextPosition(leftPt);
        }

        // 2. Create style
        Style bulletStyle  = getStyleIgnoreCase(document, "listbullet");

        if (bulletStyle  == null) {
            // Not found → create
            bulletStyle  = document.getStyles().add(StyleType.PARAGRAPH, "List Bullet");
        } else if (!"List Bullet".equals(bulletStyle.getName())) {
            // Found, but name differs
            Style alreadyCorrect = document.getStyles().get("List Bullet");
            if (alreadyCorrect == null) {
                // Safe to rename
                bulletStyle .setName("List Bullet");
            } else {
                // Both exist → better to just reuse the correct one
                bulletStyle  = alreadyCorrect;
            }
        }


//        Style bulletStyle = document.getStyles().add(StyleType.PARAGRAPH, "List Bullet");
        bulletStyle.setBaseStyleName("Normal");
        bulletStyle.setNextParagraphStyleName("List Bullet");
        bulletStyle.isQuickStyle(true);
        bulletStyle.getListFormat().setList(bulletList);

        // 3. Set paragraph format once
        ParagraphFormat pf = bulletStyle.getParagraphFormat();
        pf.setSpaceBefore(0);
        pf.setSpaceAfter(3);
        pf.setLineSpacing(12);
        pf.setLineSpacingRule(LineSpacingRule.MULTIPLE);
        pf.setLeftIndent(18);
        pf.setRightIndent(0);
        pf.setFirstLineIndent(-18);
        pf.getTabStops().clear();
        pf.getTabStops().add(18, TabAlignment.LEFT, TabLeader.NONE);
        return new ListInfo(bulletList.getListId(), bulletStyle);
    }



    public ListInfo createListBulletIndent(Document document) {
        // Create a bullet list
        com.aspose.words.List bulletList = document.getLists().add(ListTemplate.BULLET_DEFAULT);

        // Define bullet symbols (●, ‒)
        String[] bulletSymbols = {"\uF0B7", "\uF02D"};

        for (int i = 0; i < 9; i++) {
            ListLevel level = bulletList.getListLevels().get(i);
            level.getFont().setName("Symbol");
            level.setNumberFormat(bulletSymbols[i % 2]);

            // POI twips: 360 twips = 18 points
            double leftPt = 36.0 + (i * 18.0); // 720 + i * 720 twips → 36pt + i * 36pt
            double hangingPt = 18.0; // 720 twips = 36 points

            level.setNumberPosition(hangingPt);
            level.setTextPosition(leftPt);   // where text starts

        }

        // 3. Create a paragraph style linked to the numbering
        Style bulletStyle  = getStyleIgnoreCase(document, "listbulletindent");

        if (bulletStyle  == null) {
            // Not found → create
            bulletStyle  = document.getStyles().add(StyleType.PARAGRAPH, "List Bullet Indent");
        } else if (!"List Bullet Indent".equals(bulletStyle.getName())) {
            // Found, but name differs
            Style alreadyCorrect = document.getStyles().get("List Bullet Indent");
            if (alreadyCorrect == null) {
                // Safe to rename
                bulletStyle .setName("List Bullet Indent");
            } else {
                // Both exist → better to just reuse the correct one
                bulletStyle  = alreadyCorrect;
            }
        }

//        Style bulletStyle = document.getStyles().add(StyleType.PARAGRAPH, "List Bullet Indent");
        bulletStyle.setBaseStyleName("Normal");
        bulletStyle.setNextParagraphStyleName("List Bullet Indent");
        bulletStyle.isQuickStyle(true);               // <w:qFormat>
        bulletStyle.getParagraphFormat().setStyleIdentifier(StyleIdentifier.NORMAL);
        bulletStyle.getListFormat().setList(bulletList);

        // 3. Set paragraph spacing (before, after, line spacing)
        ParagraphFormat pf = bulletStyle.getParagraphFormat();
        pf.setSpaceBefore(0);                   // <w:spacing w:before="0"/>
        pf.setSpaceAfter(3);                    // 60 twips = 3 points
        pf.setLineSpacing(12);                  // 240 twips = 12 points
        pf.setLineSpacingRule(LineSpacingRule.MULTIPLE);
        pf.setLeftIndent(18);                   // 360 twips = 18 points
        pf.setRightIndent(0);
        pf.setFirstLineIndent(-18);             // Hanging indent = -360 twips
        pf.getTabStops().add(18, TabAlignment.LEFT, TabLeader.NONE); // <w:tab w:pos="360"/>

        return new ListInfo(bulletList.getListId(), bulletStyle);
    }

    public ListInfo createOutlineNumbering(Document document) {

        DocumentBuilder builder = new DocumentBuilder(document);
        // 1. Create an outline numbering list
        com.aspose.words.List outlineList = document.getLists().add(ListTemplate.OUTLINE_NUMBERS);

        // 2. Define level formats (matching Apache POI twips)
        Object[][] levels = new Object[][]{
                {NumberStyle.ARABIC,           "%1)",  720, 360},
                {NumberStyle.LOWERCASE_LETTER, "%2)", 1080, 360},
                {NumberStyle.LOWERCASE_ROMAN,  "%3)", 1440, 360},
                {NumberStyle.ARABIC,           "(%4)", 1800, 360},
                {NumberStyle.LOWERCASE_LETTER, "(%5)", 2160, 360},
                {NumberStyle.LOWERCASE_ROMAN,  "(%6)", 2520, 360},
                {NumberStyle.ARABIC,           "%7.", 2880, 360},
                {NumberStyle.LOWERCASE_LETTER, "%8.", 3240, 360},
                {NumberStyle.LOWERCASE_ROMAN,  "%9.", 3600, 360}
        };

        for (int i = 0; i < levels.length; i++) {
            ListLevel level = outlineList.getListLevels().get(i);
            level.setNumberStyle((int) levels[i][0]);
            level.setNumberFormat((String) levels[i][1]);

            int leftTwips = (int) levels[i][2];       // total left
            int hangingTwips = (int) levels[i][3];    // hanging

            double leftPts    = leftTwips / 20.0;     // convert twips -> points
            double hangingPts = hangingTwips / 20.0;

            // Set indentation
            level.setTextPosition(leftPts);                  // <w:ind w:left>
            level.setNumberPosition(leftPts - hangingPts);  // <w:ind w:hanging>
            level.setTabPosition(leftPts);                  // tab stop
        }

        // 3. Create a paragraph style linked to the numbering
        Style outlineStyle = getStyleIgnoreCase(document, "outlinenumbering");

        if (outlineStyle == null) {
            // Not found → create
            outlineStyle = document.getStyles().add(StyleType.PARAGRAPH, "Outline Numbering");
        } else if (!"Outline Numbering".equals(outlineStyle.getName())) {
            // Found, but name differs
            Style alreadyCorrect = document.getStyles().get("Outline Numbering");
            if (alreadyCorrect == null) {
                // Safe to rename
                outlineStyle.setName("Outline Numbering");
            } else {
                // Both exist → better to just reuse the correct one
                outlineStyle = alreadyCorrect;
            }
        }

//        Style outlineStyle = document.getStyles().add(StyleType.PARAGRAPH, "Outline Numbering");
        outlineStyle.setBaseStyleName("Normal");     // <w:basedOn>
        outlineStyle.setNextParagraphStyleName("Outline Numbering"); // <w:next>
        outlineStyle.isQuickStyle(true);
        outlineStyle.getListFormat().setList(outlineList);

        // 4. Paragraph spacing
        ParagraphFormat pf = outlineStyle.getParagraphFormat();
        pf.setSpaceBefore(0);          // <w:spacing w:before="0"/>
        pf.setSpaceAfter(3);           // <w:spacing w:after="60 twips" = 3 points>
        pf.setLineSpacing(12);         // <w:spacing w:line="240 twips" = 12 points>
        pf.setLineSpacingRule(LineSpacingRule.MULTIPLE);

        // 5. Indentation
        pf.setLeftIndent(18);          // 360 twips = 18 pts
        pf.setRightIndent(0);
        pf.setFirstLineIndent(-18);    // hanging indent

        // 6. Tab stops
        pf.getTabStops().add(18, TabAlignment.LEFT, TabLeader.NONE);

//            outlineStyle.getListFormat().setListLevelNumber(0);

        return new ListInfo(outlineList.getListId(), outlineStyle);

    }

    public ListInfo createNumberingListBulletSmall(Document document) {
        // Create a bullet list
        com.aspose.words.List bulletList = document.getLists().add(ListTemplate.BULLET_DEFAULT);

        // Define bullet symbols (●, ‒)
        String[] bulletSymbols = {"\uF0B7", "\uF02D"};

        for (int i = 0; i < 9; i++) {
            ListLevel level = bulletList.getListLevels().get(i);

            // Bullet character + font
            level.getFont().setName("Symbol");
            level.setNumberFormat(bulletSymbols[i % 2]);

            // POI twips: 360 twips = 18 points
            double leftPt = 18.0 + (i * 18.0); // 360 + i*360 twips → 18pt + i*18pt
            double hangingPt = 18.0; // 360 twips = 18 points

            level.setNumberPosition(hangingPt);
            level.setTextPosition(leftPt);   // where text starts

            level.getFont().setSize(10);          // smaller bullet size

        }

        // Create a custom style linked to this list
        Style bulletStyle  = getStyleIgnoreCase(document, "listbulletsmall");

        if (bulletStyle  == null) {
            // Not found → create
            bulletStyle  = document.getStyles().add(StyleType.PARAGRAPH, "List Bullet Small");
        } else if (!"List Bullet Small".equals(bulletStyle.getName())) {
            // Found, but name differs
            Style alreadyCorrect = document.getStyles().get("List Bullet Small");
            if (alreadyCorrect == null) {
                // Safe to rename
                bulletStyle .setName("List Bullet Small");
            } else {
                // Both exist → better to just reuse the correct one
                bulletStyle  = alreadyCorrect;
            }
        }

//        Style bulletStyle = document.getStyles().add(StyleType.PARAGRAPH, "List Bullet Small");
        bulletStyle.setBaseStyleName("Normal");
        bulletStyle.setNextParagraphStyleName("List Bullet Small");

        bulletStyle.isQuickStyle(true);               // <w:qFormat>
        bulletStyle.getParagraphFormat().setStyleIdentifier(StyleIdentifier.NORMAL);
        bulletStyle.getListFormat().setList(bulletList);

        // 3. Set paragraph spacing (before, after, line spacing)
        ParagraphFormat pf = bulletStyle.getParagraphFormat();
        pf.setSpaceBefore(0);                   // <w:spacing w:before="0"/>
        pf.setSpaceAfter(3);                    // 60 twips = 3 points
        pf.setLineSpacing(12);                  // 240 twips = 12 points
        pf.setLineSpacingRule(LineSpacingRule.MULTIPLE);

        // 4. Set indentation
        pf.setLeftIndent(18);                   // 360 twips = 18 points
        pf.setRightIndent(0);
        pf.setFirstLineIndent(-18);             // Hanging indent = -360 twips

        // 5. Add tab stops
        pf.getTabStops().add(18, TabAlignment.LEFT, TabLeader.NONE); // <w:tab w:pos="360"/>

        return new ListInfo(bulletList.getListId(), bulletStyle);
    }

    Style getStyleIgnoreCase(Document doc, String name) {
        if (name == null) return null;
        for (Style s : doc.getStyles()) {
            if (getTextFromStyle(s.getName()).equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null; // not found
    }


    public void createAndConfigureStyle(Document document, DocumentStyleDTO styleDTO, Map<String, ListInfo> listIds) throws Exception {

        DocumentBuilder builder = new DocumentBuilder(document);
        StyleCollection styles = document.getStyles();
        ListCollection lists = document.getLists();

        // 1. Create style (paragraph or character)
        String target = getTextFromStyle(styleDTO.getStyleId());
        Style style = null;

        // Search for existing style ignoring case + trimming spaces
        for (Style style1 : styles) {
            if (style1.getName() != null) {
                String styleName = getTextFromStyle(style1.getName());
                if (styleName.equals(target)) {
                    style = style1;
                    break;
                }
            }
        }

        // If style not found, create a new one
        if (style == null) {
            int type = StyleType.PARAGRAPH;
            if (styleDTO.getStyleId().equalsIgnoreCase("InstructionText")
                    || styleDTO.getStyleId().equalsIgnoreCase("Subscript")
                    || styleDTO.getStyleId().equalsIgnoreCase("Superscript")) {
                type = StyleType.CHARACTER;
            }
            style = styles.add(type, styleDTO.getStyleId().trim());
        }

        // 2. Apply base style if present
        if (styleDTO.getBasedOn() != null) {
            Style baseStyle = styles.get(styleDTO.getBasedOn());
            if (baseStyle != null && baseStyle.getType() == style.getType()) {
                style.setBaseStyleName(styleDTO.getBasedOn());
                if (style.getType() == StyleType.PARAGRAPH) {
                    style.setNextParagraphStyleName(styleDTO.getBasedOn());
                }
            }
        }

        // 3. Heading levels (fix: Aspose needs headingLevel, not headingLevel-1 to match POI)
        if (styleDTO.getStyleName().matches("Heading\\s\\d+") ||
                styleDTO.getStyleName().matches("CSR-Heading\\s\\d+")) {

            int headingLevel = Integer.parseInt(styleDTO.getStyleName().split(" ")[1]);
            if (style.getType() == StyleType.PARAGRAPH) {
                ParagraphFormat pf = style.getParagraphFormat();
                if (pf != null) {
                    pf.setOutlineLevel(headingLevel); // ✅ match POI (was headingLevel-1 before)
                }
            }
            style.isQuickStyle(true);
        }

        ParagraphFormat pf = style.getParagraphFormat();

        if (pf != null) {
            // 4. Indentation (fix: mimic POI logic for hanging indent)
            double leftIndentPoints = (styleDTO.getLeftIndentInches() > 0
                    ? styleDTO.getLeftIndentInches()
                    : styleDTO.getHangingIndentInches()) * 72;
            double hangingIndentPoints = styleDTO.getHangingIndentInches() * 72;

            if (styleDTO.getHangingIndentInches() > 0 || styleDTO.getLeftIndentInches() > 0 || styleDTO.getRightIndentInches() > 0) {
                pf.setLeftIndent(leftIndentPoints);
                pf.setRightIndent(styleDTO.getRightIndentInches() * 72);
                pf.setFirstLineIndent(-hangingIndentPoints); // ✅ POI equivalent (negative first line = hanging)
            }

            // 5. Widow/orphan control
            if (styleDTO.isWidowOrphanControl()) {
                pf.setWidowControl(true);
            }

            // 6. Font
            Font font = style.getFont();
            if (styleDTO.getFontFamily() != null) {
                font.setName(styleDTO.getFontFamily());
                font.setNameAscii(styleDTO.getFontFamily());
                font.setNameOther(styleDTO.getFontFamily());
                font.setNameBi(styleDTO.getFontFamily());
                font.setNameFarEast(styleDTO.getFontFamily());
            }
            if (styleDTO.getFontSize() > 0) font.setSize(styleDTO.getFontSize());
            if (styleDTO.isBold()) font.setBold(true);
            if (styleDTO.isItalic()) font.setItalic(true);

            // ✅ Fix font color (use DTO value instead of hardcoded RED)
            if (styleDTO.getFontColor() != null) {
                java.awt.Color awtColor = java.awt.Color.decode("#" + styleDTO.getFontColor());
                font.setColor(awtColor);
            }

            // 7. Keep with next / keep lines
            if (styleDTO.isKeepWithNext()) {
                pf.setKeepWithNext(true);
                pf.setKeepTogether(true);
            }

            // 8. Line spacing (fix: match POI's twip-based calc)
            if (styleDTO.getLineSpacing() > 0 || styleDTO.getSpacingBeforePts() > 0 || styleDTO.getSpacingAfterPts() > 0) {
                pf.setSpaceBefore(styleDTO.getSpacingBeforePts());
                pf.setSpaceAfter(styleDTO.getSpacingAfterPts());

                // ✅ POI uses: spacing.setLine(BigInteger.valueOf(lineSpacing * 240))
                // Aspose expects points → 240 twips = 12 points
                pf.setLineSpacing(styleDTO.getLineSpacing() * 12);
                pf.setLineSpacingRule(LineSpacingRule.MULTIPLE);
            }

            // 9. Tab stops
            if (styleDTO.getTabStops() != null) {
                TabStopCollection tabStops = pf.getTabStops();
                tabStops.clear();
                for (TabStopDTO tabStop : styleDTO.getTabStops()) {
                    int alignment = tabAlignment(tabStop.getAlignment());
                    double positionPoints = tabStop.getPositionInches() * 72;
                    int leader = tabLeader(tabStop.getLeader());
                    tabStops.add(positionPoints, alignment, leader);
                }
            }
        }

        // 10. Reuse existing bullet lists (same as POI mapping abstractNumIds)
        List<String> bulletStyles = Arrays.asList("ListBullet", "ListBulletSmall", "ListbulletIndent", "OutlineNumbering");
        if (bulletStyles.contains(styleDTO.getStyleId())) {
            ListInfo listInfo = listIds.get(styleDTO.getStyleId());
            if (listInfo != null) {
                com.aspose.words.List bulletList = document.getLists().getListByListId(listInfo.getListId());
                style.getListFormat().setList(bulletList);
                style.isQuickStyle(true);
                style.setNextParagraphStyleName(style.getName());
                style.getListFormat().getList().isRestartAtEachSection(true);
            }
        }
    }
    
    public String getTextFromStyle(String name) {
        name=StringUtils.replace(name, StringEscapeUtils.unescapeJava("\u2006"),"").toLowerCase();
        name=StringUtils.replace(name,StringEscapeUtils.unescapeJava("\u00A0")," ").toLowerCase();
        name=removeHypenAndUnderscore(name);
        name=name.replace("\u00A0"," ").toLowerCase();
        name=name.replaceAll("\\p{IsWhite_Space}+", "").toLowerCase();
        return name;
    }

    private String removeHypenAndUnderscore(String text) {
        String regex = "(-|_){3,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.replaceAll("") : text;

    }

    private int tabAlignment(String alignment) {
        if (alignment == null) return TabAlignment.LEFT;

        switch (alignment.toLowerCase()) {
            case "left": return TabAlignment.LEFT;
            case "center": return TabAlignment.CENTER;
            case "right": return TabAlignment.RIGHT;
            case "num":
            case "decimal": return TabAlignment.DECIMAL;
            default: return TabAlignment.LEFT; // fallback
        }
    }

    private int tabLeader(String leader) {
        if (leader == null) return TabLeader.NONE;

        switch (leader.toLowerCase()) {
            case "dot":
            case "period":     return TabLeader.DOTS;
            case "middleDot":  return TabLeader.MIDDLE_DOT;
            case "hyphen":
            case "dash":       return TabLeader.DASHES;
//            case "underscore": return TabLeader.;
//            case "thick":      return TabLeader.THICK_LINE;
//            case "equals":     return TabLeader.EQUAL_SIGN;
            default:           return TabLeader.NONE; // fallback
        }
    }


    private void  updateStyleProperites(DocumentStyleDTO styleDTO, Style style) {

        Font font = style.getFont();
        font.clearFormatting(); // clears font size, name, bold, italic, colors etc.

        if (style.getType() == StyleType.PARAGRAPH) {
            ParagraphFormat paraFormat = style.getParagraphFormat();
            paraFormat.clearFormatting(); // clears spacing, indents, alignment, tabs, widow/orphan etc.
        }

        // Font
        if (styleDTO.getFontFamily() != null) font.setName(styleDTO.getFontFamily());
        if (styleDTO.getFontSize() > 0) font.setSize(styleDTO.getFontSize());
        font.setBold(styleDTO.isBold());
        font.setItalic(styleDTO.isItalic());
        if (styleDTO.getFontColor() != null) {
            font.setColor(Color.RED);
        }


        // Paragraph formatting
        if (style.getType() == StyleType.PARAGRAPH) {
            ParagraphFormat paraFormat = style.getParagraphFormat();

            // Handle bullet style exceptions
            List<String> bulletStyles = Arrays.asList("ListBulletSmall", "ListBullet", "ListBulletIndent", "OutlineNumbering");
            boolean isBulletStyle = bulletStyles.stream().anyMatch(x -> x.equalsIgnoreCase(styleDTO.getStyleId()));

            if ((styleDTO.getHangingIndentInches() > 0 || styleDTO.getLeftIndentInches() > 0 || styleDTO.getRightIndentInches() > 0) && !isBulletStyle) {
                if (styleDTO.getLeftIndentInches() > 0) paraFormat.setLeftIndent(styleDTO.getLeftIndentInches() * 72);
                if (styleDTO.getRightIndentInches() > 0) paraFormat.setRightIndent(styleDTO.getRightIndentInches() * 72);
                if (styleDTO.getHangingIndentInches() > 0) paraFormat.setFirstLineIndent(-styleDTO.getHangingIndentInches() * 72);
            }

            // Spacing
            if (styleDTO.getLineSpacing() > 0) {
                paraFormat.setLineSpacing(styleDTO.getLineSpacing() * 12);
                paraFormat.setLineSpacingRule(LineSpacingRule.EXACTLY);
            }
            if (styleDTO.getSpacingBeforePts() > 0) paraFormat.setSpaceBefore(styleDTO.getSpacingBeforePts());
            if (styleDTO.getSpacingAfterPts() > 0) paraFormat.setSpaceAfter(styleDTO.getSpacingAfterPts());

            // Widow/Orphan & KeepWithNext
            paraFormat.setWidowControl(styleDTO.isWidowOrphanControl());
            if (styleDTO.isKeepWithNext()) {
                paraFormat.setKeepTogether(true);
                paraFormat.setKeepWithNext(true);
            }

            if (styleDTO.getTabStops() != null) {
                style.getParagraphFormat().getTabStops().clear();
                for (TabStopDTO tabStop : styleDTO.getTabStops()) {
                    double pos = tabStop.getPositionInches() * 72;

                    int alignment = TabAlignment.LEFT; // default
                    if (tabStop.getAlignment() != null) {
                        switch (tabStop.getAlignment().toLowerCase()) {
                            case "center":
                                alignment = TabAlignment.CENTER;
                                break;
                            case "right":
                                alignment = TabAlignment.RIGHT;
                                break;
                            case "decimal":
                                alignment = TabAlignment.DECIMAL;
                                break;
                            case "bar":
                                alignment = TabAlignment.BAR;
                                break;
                            default:
                                alignment = TabAlignment.LEFT;
                        }
                    }

                    int leader = TabLeader.NONE; // default
                    if (tabStop.getLeader() != null) {
                        switch (tabStop.getLeader().toLowerCase()) {
                            case "dot":
                                leader = TabLeader.DOTS;
                                break;
                            case "dashes":
                                leader = TabLeader.DASHES;
                                break;
                            default:
                                leader = TabLeader.NONE;
                        }
                    }

                    style.getParagraphFormat().getTabStops().add(pos, alignment, leader);
                }
            }
        }
    }


}

