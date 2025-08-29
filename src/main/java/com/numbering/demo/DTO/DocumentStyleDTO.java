package com.numbering.demo.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DocumentStyleDTO {
    public String styleId;
    public String styleName;
    public String basedOn;
    public String fontFamily;
    public int fontSize;
    public boolean italic;
    public boolean bold;
    public String fontColor;
    public int spacingBeforePts;
    public int spacingAfterPts;
    public double hangingIndentInches;
    public double leftIndentInches;
    public double rightIndentInches;
    public double lineSpacing;
    public boolean widowOrphanControl;
    public List<TabStopDTO> tabStops;
    public boolean keepWithNext;
    public boolean keeplinestogether;
    private List<String> matchedKeys;
}
