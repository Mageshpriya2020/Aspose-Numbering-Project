package com.numbering.demo.NUM_PACKAGE;

import com.aspose.words.*;
import com.numbering.demo.DTO.AppConstants;
import com.numbering.demo.DTO.DocumentStyleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

@Service
public class StyleService {

    @Autowired
    NUMService ns;

    public void updateStyleAndNumbering(Document doc, List<DocumentStyleDTO> styles, Map<String, ListInfo> listIds) {

        // Track which list ids (numIds) we've initialized (set StartAt, etc.)
        Set<Integer> initializedListIds = new HashSet<>();

        for (Paragraph para : (Iterable<Paragraph>) doc.getChildNodes(NodeType.PARAGRAPH, true)) {

            // --- 1) Capture the paragraph's ORIGINAL numbering before we touch anything ---
            Integer originalListId = null;
            Integer originalLevel  = null;
            if (para.getListFormat().isListItem()) {
                originalListId = para.getListFormat().getList().getListId();      // <- this is the paragraph's numId
                originalLevel  = para.getListFormat().getListLevelNumber();
            }

            // --- 2) Resolve your configured style mapping (unchanged from your logic) ---
            String currentStyleName = ns.getTextFromStyle(para.getParagraphFormat().getStyleName());
            DocumentStyleDTO matchedStyle = styles.stream()
                    .filter(s -> s.getMatchedKeys() != null
                            && !s.getMatchedKeys().isEmpty()
                            && s.getMatchedKeys().stream().anyMatch(k -> k.toLowerCase().equalsIgnoreCase(currentStyleName)))
                    .findFirst()
                    .orElse(null);

            if (matchedStyle == null) {
                // If it had original numbering, keep it; if not, move on
                continue;
            }

            // --- 3) Apply the paragraph style FIRST (so it doesn't overwrite our list later) ---
            if (listIds != null && listIds.containsKey(matchedStyle.getStyleId())) {
                ListInfo li = listIds.get(matchedStyle.getStyleId());
                Style styleInDoc = doc.getStyles().get(li.getStyle().getName());
                if (styleInDoc != null) {
                    para.getParagraphFormat().setStyle(styleInDoc);
                }
            }

            // --- 4) Re-bind the ORIGINAL list (numId) so numbering restarts when numId changes ---
            if (originalListId != null && matchedStyle.getStyleId().equalsIgnoreCase("OutlineNumbering")) {
                com.aspose.words.List originalList = doc.getLists().getListByListId(originalListId);
                if (originalList != null) {
                    // DO NOT removeNumbers(); that would lose the original numId link.
                    para.getListFormat().setList(originalList);
                    if (originalLevel != null) {
                        // ✅ Only re-apply level if it is NOT a bullet
                        ListLevel lvl = originalList.getListLevels().get(originalLevel);
                        if (lvl.getNumberStyle() != NumberStyle.BULLET) {
                        para.getListFormat().setListLevelNumber(originalLevel);
                    }
                    }

                    // Initialize this list id only once (numbers start at 1)
                    if (!initializedListIds.contains(originalListId)) {
                        for (int i = 0; i < originalList.getListLevels().getCount(); i++) {
                            // Bullets ignore StartAt; safe to set for numbered levels.
                            if (originalList.getListLevels().get(i).getNumberStyle() != NumberStyle.BULLET) {
                                originalList.getListLevels().get(i).setStartAt(1);
                            }
                        }
                    }
                }
//                continue;
            }
        }
    }


    public void applyDocumentFormatting(Document document, List<DocumentStyleDTO> styles, Map<String, ListInfo> listofIds) {

        try{

         //Get All nodes
            NodeCollection nodeCollection = document.getChildNodes(NodeType.ANY,true);

            for(Object obj: nodeCollection){
                  Node node = (Node) obj;
                  if(node.getNodeType() ==  NodeType.TABLE){

                      Table table = (Table) node;

                      for(Row row : table.getRows()){
                          for(Cell cell : row.getCells()){
                              for (Paragraph paragraph : cell.getParagraphs()){

                                  String styleName = paragraph.getParagraphFormat().getStyle().getName();

                                  if(paragraph !=null && paragraph.getParagraphFormat() != null && paragraph.getParagraphFormat().getStyle() !=null
                                          && AppConstants.LIST_BULLET.stream().anyMatch(s -> paragraph.getParagraphFormat().getStyle().getName().equalsIgnoreCase(s))) {

                                      setHangingIndent(paragraph,styleName,document,listofIds);

                                  } else if (paragraph !=null && paragraph.getParagraphFormat() != null && paragraph.getParagraphFormat().getStyle() !=null
                                          && paragraph.getParagraphFormat().getStyle().getName().equalsIgnoreCase("OutlineNumbering")) {


                                      outlienumberSpace(paragraph);

                                  } else if (paragraph !=null && paragraph.getParagraphFormat() != null && paragraph.getParagraphFormat().getStyle() !=null
                                          && paragraph.getParagraphFormat().getStyle().getName().equalsIgnoreCase("Normal")) {

                                      ParagraphFormat format = paragraph.getParagraphFormat();
                                      format.setSpaceBefore(0);
                                      format.setSpaceAfter(0);

                                  } else if (paragraph !=null && paragraph.getParagraphFormat() != null && paragraph.getParagraphFormat().getStyle() !=null
                                          && paragraph.getParagraphFormat().getStyle().getName().equalsIgnoreCase("NormalSingle")) {

                                      ParagraphFormat format = paragraph.getParagraphFormat();
                                      // Directly reset spacing and indentation
                                      format.setSpaceBefore(0);
                                      format.setSpaceAfter(0);
                                      format.setLineSpacing(12);  // standard single spacing  240/20 = 12pt line spacing
                                      format.setLineSpacingRule(LineSpacingRule.MULTIPLE);
                                  } else if (paragraph !=null && paragraph.getParagraphFormat() != null && paragraph.getParagraphFormat().getStyle() !=null
                                          && paragraph.getParagraphFormat().getStyle().getName().equalsIgnoreCase("Caption")) {
                                      captionFontSizeSet(paragraph);
                                  }
                              }
                          }
                      }


                  } else if (node.getNodeType() == NodeType.PARAGRAPH) {
                      Paragraph paragraph = (Paragraph) node;
                      String styleName = paragraph.getParagraphFormat().getStyle().getName();
                      if(paragraph !=null && paragraph.getParagraphFormat() != null && paragraph.getParagraphFormat().getStyle() !=null
                              && AppConstants.LIST_BULLET.stream().anyMatch(s -> paragraph.getParagraphFormat().getStyle().getName().equalsIgnoreCase(s))) {
                          setHangingIndent(paragraph,styleName,document,listofIds);
                      }  else if (paragraph !=null && paragraph.getParagraphFormat() != null && paragraph.getParagraphFormat().getStyle() !=null
                              && paragraph.getParagraphFormat().getStyle().getName().equalsIgnoreCase("Normal")) {

                          ParagraphFormat format = paragraph.getParagraphFormat();
                          format.setSpaceBefore(0);
                          format.setSpaceAfter(0);

                      }   else if (paragraph !=null && paragraph.getParagraphFormat() != null && paragraph.getParagraphFormat().getStyle() !=null
                              && paragraph.getParagraphFormat().getStyle().getName().equalsIgnoreCase("NormalSingle")) {

                          ParagraphFormat format = paragraph.getParagraphFormat();
                          // Directly reset spacing and indentation
                          format.setSpaceBefore(0);
                          format.setSpaceAfter(0);
                          format.setLineSpacing(12);  // standard single spacing  240/20 = 12pt line spacing
                          format.setLineSpacingRule(LineSpacingRule.MULTIPLE);
                      } else if (paragraph !=null && paragraph.getParagraphFormat() != null && paragraph.getParagraphFormat().getStyle() !=null
                              && paragraph.getParagraphFormat().getStyle().getName().equalsIgnoreCase("Caption")) {
                          captionFontSizeSet(paragraph);
                      }   else if (paragraph !=null && paragraph.getParagraphFormat() != null && paragraph.getParagraphFormat().getStyle() !=null
                              && paragraph.getParagraphFormat().getStyle().getName().equalsIgnoreCase("TableNoteInfo")){
                              ParagraphFormat format = paragraph.getParagraphFormat();
                              format.setSpaceBefore(0);
                              format.setSpaceAfter(0);
                              format.setLeftIndent(0);
                              format.setRightIndent(0);
                              format.setFirstLineIndent(0);
                              format.getTabStops().clear();
                              for(Run run : paragraph.getRuns()){
                                  run.getFont().clearFormatting();
                                  run.getFont().setSize(10);
                              }
                      }

                  }
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
        
    }

    private void captionFontSizeSet(Paragraph paragraph) {
            ParagraphFormat format = paragraph.getParagraphFormat();

            // Clear paragraph spacing, indentation, and tabs
            format.setSpaceBefore(0);
            format.setSpaceAfter(6); // 120 twips = 6 pt
            format.setLineSpacing(12); // 240 twips = 12 pt
            format.setLineSpacingRule(LineSpacingRule.MULTIPLE);
            format.setLeftIndent(0);
            format.setFirstLineIndent(0);

            // Remove any tab stops
            format.getTabStops().clear();

            // Update font size for all runs
            for (Run run : (Iterable<Run>) paragraph.getRuns()) {
                run.getFont().setSize(12);      // set font size
                run.getFont().setSpacing(0);   // remove extra character spacing
            }
    }


    private static void outlienumberSpace(Paragraph paragraph){
        ParagraphFormat format = paragraph.getParagraphFormat();

        // Directly reset spacing and indentation
        format.setLeftIndent(0);
        format.setFirstLineIndent(0);
        format.setSpaceBefore(0);
        format.setSpaceAfter(0); 
        
    }

    private void setHangingIndent(Paragraph paragraph, String styleName, Document document, Map<String, ListInfo> listofIds) {

        ParagraphFormat format = paragraph.getParagraphFormat();

        // Directly reset spacing and indentation
        format.setLeftIndent(0);
        format.setFirstLineIndent(0);
        format.setSpaceBefore(0);
        format.setSpaceAfter(0);

       if(styleName.equalsIgnoreCase("ListBullet")){

           if(paragraph.isListItem()){
               paragraph.getListFormat().removeNumbers();
           }
           com.aspose.words.List list =  document.getLists().getListByListId(listofIds.get(0).getListId());
           paragraph.getListFormat().setList(list);
       } else if (styleName.equalsIgnoreCase("ListBulletSmall")) {
           if(paragraph.isListItem()){
               paragraph.getListFormat().removeNumbers();
           }
           com.aspose.words.List list =  document.getLists().getListByListId(listofIds.get(2).getListId());
           paragraph.getListFormat().setList(list);
       }

    }

    public static void normalizeParagraphStyles(Document document, Map<String, ListInfo> listOfIds) throws Exception {
        for (Paragraph para : (Iterable<Paragraph>) document.getChildNodes(NodeType.PARAGRAPH, true)) {
            ParagraphFormat paragraphFormat = para.getParagraphFormat();

            // ✅ Handle BodyText: reset spacing + indents
            if ("BodyText".equalsIgnoreCase(paragraphFormat.getStyleName())) {
                paragraphFormat.setSpaceAfter(0);
                paragraphFormat.setSpaceBefore(0);
                paragraphFormat.setFirstLineIndent(0);
                paragraphFormat.setLeftIndent(0);
                paragraphFormat.setRightIndent(0);
            }

            // ✅ Handle ListParagraph: map to OutlineNumbering / ListBullet
            else if ("ListParagraph".equalsIgnoreCase(paragraphFormat.getStyleName()) && para.isListItem()) {
                Integer numId = para.getListFormat().getList().getListId();
                Integer iLevel = para.getListFormat().getListLevelNumber();

                if (numId != null && iLevel != null) {
                    ListLevel level = para.getListFormat().getListLevel();
                    if (level != null && level.getTextPosition() > 0) { // indent check
                        int numStyle = level.getNumberStyle();

                        if (numStyle == NumberStyle.ARABIC || numStyle == NumberStyle.LOWERCASE_LETTER) {
                            // Equivalent to OutlineNumbering
                            com.aspose.words.List list = document.getLists().getListByListId(listOfIds.get(3).getListId());
                            paragraphFormat.setStyleName(list.getStyle().getName());
                        }
                        else if (numStyle == NumberStyle.BULLET) {
                            // Equivalent to ListBullet
                            com.aspose.words.List list = document.getLists().getListByListId(listOfIds.get(0).getListId());
                            paragraphFormat.setStyleName(list.getStyle().getName());
                        }
                    }
                }
            }
        }
    }

}







