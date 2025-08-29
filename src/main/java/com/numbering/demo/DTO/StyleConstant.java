package com.numbering.demo.DTO;

public class StyleConstant {

        public static final String docStyle = """ 
                [
                {
                    "basedOn": null,
                    "fontColor": null,
                    "fontFamily": "Times New Roman",
                    "fontSize": 12,
                    "hangingIndentInches": 0.0,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.25,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 6,
                    "spacingBeforePts": 0,
                    "styleId": "Normal",
                    "styleName": "Normal",
                    "tabStops": null,
                    "tabString": "",
                    "widowOrphanControl": true,
                    "keepWithNext":false,
                    "keeplinestogether":false,
                    "matchedKeys": ["NormalSingle", "StandardSingle", "BodyTextSingle"]       
                },
                {
                    "basedOn": null,
                    "fontColor": null,
                    "fontFamily": "Times New Roman",
                    "fontSize": 12,
                    "hangingIndentInches": 0.0,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 0,
                    "spacingBeforePts": 0,
                    "styleId": "NormalSingle",
                    "styleName": "Normal Single",
                    "tabStops": null,
                    "tabString": "",
                    "widowOrphanControl": true,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 0,
                    "hangingIndentInches": 0.0,
                    "italic": false,
                    "leftIndentInches": 0.25,
                    "lineSpacing": 0.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 0,
                    "spacingBeforePts": 0,
                    "styleId": "Normalindent",
                    "styleName": "Normal indent",
                    "tabStops": null,
                    "tabString": "",
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": "Arial",
                    "fontSize": 14,
                    "hangingIndentInches": 0.44,
                    "bold": true,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 12,
                    "spacingBeforePts": 0,
                    "styleId": "Heading1",
                    "styleName": "Heading 1",
                    "tabStops": [
                        {
                            "alignment": "NUM",
                                "leader": null,
                                "positionInches": 0.44
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":true,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": "Arial",
                    "fontSize": 0,
                    "hangingIndentInches": 0.69,
                    "italic": false,
                    "bold": true,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 12,
                    "spacingBeforePts": 0,
                    "styleId": "Heading2",
                    "styleName": "Heading 2",
                    "tabStops": [
                        {
                            "alignment": "NUM",
                                "leader": null,
                                "positionInches": "0.69"
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":true,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": "Arial",
                    "fontSize": 0,
                    "hangingIndentInches": 0.8,
                    "bold": true,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 12,
                    "spacingBeforePts": 0,
                    "styleId": "Heading3",
                    "styleName": "Heading 3",
                    "tabStops": [
                        {
                            "alignment": "NUM",
                                "leader": null,
                                "positionInches": "0.8"
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":true,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": "Arial",
                    "fontSize": 0,
                    "hangingIndentInches": 0.95,
                    "bold": true,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 9,
                    "spacingBeforePts": 0,
                    "styleId": "Heading4",
                    "styleName": "Heading 4",
                    "tabStops": [
                        {
                            "alignment": "NUM",
                                "leader": null,
                                "positionInches": "0.95"
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":true,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": "Arial",
                    "fontSize": 0,
                    "hangingIndentInches": 1.1,
                    "bold": true,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 6,
                    "spacingBeforePts": 0,
                    "styleId": "Heading5",
                    "styleName": "Heading 5",
                    "tabStops": [
                        {
                            "alignment": " NUM",
                                "leader": null,
                                "positionInches": 1.1
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":true,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": "Arial",
                    "fontSize": 0,
                    "hangingIndentInches": 1.25,
                    "bold": true,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 6,
                    "spacingBeforePts": 0,
                    "styleId": "Heading6",
                    "styleName": "Heading 6",
                    "tabStops": [
                        {
                            "alignment": "NUM",
                                "leader": null,
                                "positionInches": 1.25
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":true,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": "Arial",
                    "fontSize": 0,
                    "hangingIndentInches": 1.35,
                    "bold": true,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 6,
                    "spacingBeforePts": 0,
                    "styleId": "Heading7",
                    "styleName": "Heading 7",
                    "tabStops": [
                        {
                            "alignment": "NUM",
                                "leader": null,
                                "positionInches": 1.35
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":true,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": "Arial",
                    "fontSize": 0,
                    "hangingIndentInches": 1.45,
                    "bold": true,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 6,
                    "spacingBeforePts": 0,
                    "styleId": "Heading8",
                    "styleName": "Heading 8",
                    "tabStops": [
                        {
                            "alignment": "NUM",
                                "leader": null,
                                "positionInches": 1.45
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":true,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": "Arial",
                    "fontSize": 14,
                    "hangingIndentInches": 0.0,
                    "bold": true,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 12,
                    "spacingBeforePts": 0,
                    "styleId": "HeadingCentered",
                    "styleName": "Heading Centered",
                    "tabStops": null,
                    "tabString": "",
                    "widowOrphanControl": false,
                    "keepWithNext":true,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 10,
                    "hangingIndentInches": 0.0,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 0,
                    "spacingBeforePts": 0,
                    "styleId": "Header",
                    "styleName": "Header",
                    "tabStops": [
                        {
                            "alignment": "CENTER",
                                "leader": null,
                                "positionInches": 3.0
                        },
                        {
                            "alignment": "RIGHT",
                                "leader": null,
                                "positionInches": 6.0
                        },
                        {
                            "alignment": "RIGHT",
                                "leader": null,
                                "positionInches": 9.0
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 10,
                    "hangingIndentInches": 0.0,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 0,
                    "spacingBeforePts": 0,
                    "styleId": "Footer",
                    "styleName": "Footer",
                    "tabStops": [
                        {
                            "alignment": "CENTER",
                                "leader": null,
                                "positionInches": 3.0
                        },
                        {
                            "alignment": "RIGHT",
                                "leader": null,
                                "positionInches": 6.0
                        },
                        {
                            "alignment": "RIGHT",
                                "leader": null,
                                "positionInches": 9.0
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 0,
                    "hangingIndentInches": 0.25,
                    "italic": false,
                    "bold": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 3,
                    "spacingBeforePts": 0,
                    "styleId": "ListBullet",
                    "styleName": "List bullet",
                    "tabStops": [
                        {
                            "alignment": "LEFT",
                                "leader": null,
                                "positionInches": 0.25
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    "keeplinestogether":false,
                    "matchedKeys": ["C-Bullet", "Listbullet","bullet"]       

                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 0,
                    "hangingIndentInches": 0.25,
                    "italic": false,
                    "bold": false,
                    "leftIndentInches": 0.25,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 3,
                    "spacingBeforePts": 0,
                    "styleId": "ListbulletIndent",
                    "styleName": "List bullet Indent",
                    "tabStops":[
                        {
                            "alignment": "LEFT",
                                "leader": null,
                                "positionInches": 0.5
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": ["C-Bullet Indented"]
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 10,
                    "hangingIndentInches": 0.25,
                    "italic": false,
                    "bold": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 3,
                    "spacingBeforePts": 0,
                    "styleId": "ListBulletSmall",
                    "styleName": "List bullet Small",
                    "tabStops":[
                        {
                            "alignment": "LEFT",
                                "leader": null,
                                "positionInches": 0.25
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 0,
                    "hangingIndentInches": 0.25,
                    "italic": false,
                    "bold": false,
                    "leftIndentInches": 0.25,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 3,
                    "spacingBeforePts": 0,
                    "styleId": "OutlineNumbering",
                    "styleName": "Outline Numbering",
                    "tabStops": null,
                    "tabString": "",
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": ["C-NumberedList"]
                },
                {
                    "basedOn": null,
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 14,
                    "hangingIndentInches": 0.0,
                    "italic": false,
                    "bold": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 0.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 0,
                    "spacingBeforePts": 0,
                    "styleId": "Superscript",
                    "styleName": "Superscript",
                    "tabStops": null,
                    "tabString": "",
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": null,
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 14,
                    "hangingIndentInches": 0.0,
                    "italic": false,
                    "bold": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 0.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 0,
                    "spacingBeforePts": 0,
                    "styleId": "Subscript",
                    "styleName": "Subscript",
                    "tabStops": null,
                    "tabString": "",
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 12,
                    "hangingIndentInches": 1.5,
                    "italic": false,
                    "bold": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 6,
                    "spacingBeforePts": 0,
                    "styleId": "Caption",
                    "styleName": "Caption",
                    "tabStops": [
                        {
                            "alignment": "LEFT",
                                "leader": null,
                                "positionInches": 1.5
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":true,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Superscript",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 0,
                    "hangingIndentInches": 0.0,
                    "italic": false,
                    "bold": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 0.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 0,
                    "spacingBeforePts": 0,
                    "styleId": "EndnoteReference",
                    "styleName": "Endnote Reference",
                    "tabStops": [
                        {
                            "alignment": "LEFT",
                                "leader": null,
                                "positionInches": "0.25"
                        }
                    ],
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                    "basedOn": "Normal Single",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 0,
                    "hangingIndentInches": 0.25,
                    "italic": false,
                    "bold": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 0.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 3,
                    "spacingBeforePts": 0,
                    "styleId": "EndnoteText",
                    "styleName": "Endnote Text",
                    "tabStops": null,
                    "tabString": "",
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    " keeplinestogether":false,
                    "matchedKeys": []
                },
                {
                      "basedOn": "Normal",
                     "fontColor": null,
                     "fontFamily": null,
                      "fontSize": 14,
                      "hangingIndentInches": 0.44,
                      "bold": true,
                       "italic": false,
                       "leftIndentInches": 0.0,
                       "lineSpacing": 1.0,
                       "rightIndentInches": 0.38,
                       "spacingAfterPts": 6,
                       "spacingBeforePts": 0,
                       "styleId": "TOC1",
                        "styleName": "toc 1",
                        "tabStops": [
                               {
                                    "alignment": "left",
                                     "leader": null,
                                     "positionInches": 0.44                   
                                },
                                 {
                                     "alignment": "right",
                                      "leader": "dot",
                                      "positionInches": 6.0
                                }
                                   ],
                         "widowOrphanControl": false,
                         "keepWithNext":false,
                         " keeplinestogether":false,
                         "matchedKeys": []
                 },
                {
                      "basedOn": "Normal",
                      "fontColor": null,
                     "fontFamily": null,
                      "fontSize": 12,
                      "hangingIndentInches": 0.69,
                      "bold": false,
                       "italic": false,
                       "leftIndentInches": 0.0,
                       "lineSpacing": 1.0,
                       "rightIndentInches": 0.38,
                       "spacingAfterPts": 6,
                       "spacingBeforePts": 0,
                       "styleId": "TOC2",
                        "styleName": "toc 2",
                        "tabStops": [
                               {
                                    "alignment": "left",
                                     "leader": null,
                                     "positionInches": 0.69                   
                                },
                                 {
                                     "alignment": "right",
                                      "leader": "dot",
                                      "positionInches": 6.0
                                }
                                   ],
                         "widowOrphanControl": false,
                         "keepWithNext":false,
                         " keeplinestogether":false,
                         "matchedKeys": []
                 },
                 {
                      "basedOn": "Normal",
                      "fontColor": null,
                     "fontFamily": null,
                      "fontSize": 12,
                      "hangingIndentInches": 0.8,
                      "bold": false,
                       "italic": false,
                       "leftIndentInches": 0.0,
                       "lineSpacing": 1.0,
                       "rightIndentInches": 0.38,
                       "spacingAfterPts": 6,
                       "spacingBeforePts": 0,
                       "styleId": "TOC3",
                        "styleName": "toc 3",
                        "tabStops": [
                               {
                                    "alignment": "left",
                                     "leader": null,
                                     "positionInches": 0.8                   
                                },
                                 {
                                     "alignment": "right",
                                      "leader": "dot",
                                      "positionInches": 6.0
                                }
                                   ],
                         "widowOrphanControl": false,
                         "keepWithNext":false,
                         " keeplinestogether":false,
                         "matchedKeys": []
                 },
                 {
                      "basedOn": "Normal",
                      "fontColor": null,
                     "fontFamily": null,
                      "fontSize": 12,
                      "hangingIndentInches": 0.95,
                      "bold": false,
                       "italic": false,
                       "leftIndentInches": 0.0,
                       "lineSpacing": 1.0,
                       "rightIndentInches": 0.38,
                       "spacingAfterPts": 6,
                       "spacingBeforePts": 0,
                       "styleId": "TOC4",
                        "styleName": "toc 4",
                        "tabStops": [
                               {
                                    "alignment": "left",
                                     "leader": null,
                                     "positionInches": 0.95                   
                                },
                                 {
                                     "alignment": "right",
                                      "leader": "dot",
                                      "positionInches": 6.0
                                }
                                   ],
                         "widowOrphanControl": false,
                          "keepWithNext":false,
                          " keeplinestogether":false,
                          "matchedKeys": []
                 },
                 {
                      "basedOn": "Normal",
                      "fontColor": null,
                     "fontFamily": null,
                      "fontSize": 12,
                      "hangingIndentInches": 1.1,
                      "bold": false,
                       "italic": false,
                       "leftIndentInches": 0.0,
                       "lineSpacing": 1.0,
                       "rightIndentInches": 0.38,
                       "spacingAfterPts": 6,
                       "spacingBeforePts": 0,
                       "styleId": "TOC5",
                        "styleName": "toc 5",
                        "tabStops": [
                               {
                                    "alignment": "left",
                                     "leader": null,
                                     "positionInches": 1.1                   
                                },
                                 {
                                     "alignment": "right",
                                      "leader": "dot",
                                      "positionInches": 6.0
                                }
                                   ],
                         "widowOrphanControl": false,
                         "keepWithNext":false,
                         " keeplinestogether":false
                 },
                 {
                      "basedOn": "Normal",
                      "fontColor": null,
                     "fontFamily": null,
                      "fontSize": 12,
                      "hangingIndentInches": 1.25,
                      "bold": false,
                       "italic": false,
                       "leftIndentInches": 0.0,
                       "lineSpacing": 1.0,
                       "rightIndentInches": 0.38,
                       "spacingAfterPts": 6,
                       "spacingBeforePts": 0,
                       "styleId": "TOC6",
                        "styleName": "toc 6",
                        "tabStops": [
                               {
                                    "alignment": "left",
                                     "leader": null,
                                     "positionInches": 1.25                   
                                },
                                 {
                                     "alignment": "right",
                                      "leader": "dot",
                                      "positionInches": 6.0
                                }
                                   ],
                         "widowOrphanControl": false,
                         "keepWithNext":false,
                          keeplinestogether":false,
                          "matchedKeys": []
                 },
                 {
                      "basedOn": "Normal",
                      "fontColor": null,
                     "fontFamily": null,
                      "fontSize": 12,
                      "hangingIndentInches": 1.35,
                      "bold": false,
                       "italic": false,
                       "leftIndentInches": 0.0,
                       "lineSpacing": 1.0,
                       "rightIndentInches": 0.38,
                       "spacingAfterPts": 6,
                       "spacingBeforePts": 0,
                       "styleId": "TOC7",
                        "styleName": "toc 7",
                        "tabStops": [
                               {
                                    "alignment": "left",
                                     "leader": null,
                                     "positionInches": 1.35                   
                                },
                                 {
                                     "alignment": "right",
                                      "leader": "dot",
                                      "positionInches": 6.0
                                }
                                   ],
                         "widowOrphanControl": false,
                          "keepWithNext":false,
                          " keeplinestogether":false,
                          "matchedKeys": []
                 },
                 {
                      "basedOn": "Normal",
                      "fontColor": null,
                     "fontFamily": null,
                      "fontSize": 12,
                      "hangingIndentInches": 1.45,
                      "bold": false,
                       "italic": false,
                       "leftIndentInches": 0.0,
                       "lineSpacing": 1.0,
                       "rightIndentInches": 0.38,
                       "spacingAfterPts": 6,
                       "spacingBeforePts": 0,
                       "styleId": "TOC8",
                        "styleName": "toc 8",
                        "tabStops": [
                               {
                                    "alignment": "left",
                                     "leader": null,
                                     "positionInches": 1.45                   
                                },
                                 {
                                     "alignment": "right",
                                      "leader": "dot",
                                      "positionInches": 6.0
                                }
                                   ],
                         "widowOrphanControl": false,
                         "keepWithNext":false,
                         " keeplinestogether":false,
                         "matchedKeys": []
                 },
                 {
                    "basedOn": "Normal",
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 10,
                    "hangingIndentInches": 0.0,
                    "italic": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 0,
                    "spacingBeforePts": 0,
                    "styleId": "TableText",
                    "styleName": "Table Text",
                    "tabStops": null,
                    "tabString": "",
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    "keeplinestogether":false,
                    "matchedKeys": []
                },
                 {
                    "basedOn": null,
                    "fontColor": "FF0000",
                    "fontFamily": null,
                    "fontSize": 12,
                    "hangingIndentInches": 0.0,
                    "italic": true,
                    "bold": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 0.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 0,
                    "spacingBeforePts": 0,
                    "styleId": "InstructionText",
                    "styleName": "Instruction Text",
                    "tabStops": null,
                    "tabString": "",
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    "keeplinestogether":false,
                    "matchedKeys": []
                },
                 {
                    "basedOn": Normal,
                    "fontColor": null,
                    "fontFamily": null,
                    "fontSize": 10,
                    "hangingIndentInches":0.15,
                    "italic": false,
                    "bold": false,
                    "leftIndentInches": 0.0,
                    "lineSpacing": 1.0,
                    "rightIndentInches": 0.0,
                    "spacingAfterPts": 0,
                    "spacingBeforePts": 0,
                    "styleId": "TableNoteInfo",
                    "styleName": "Table Note Info",
                    "tabStops": [
                        {
                            "alignment": left,
                                "leader": null,
                                "positionInches": "0.15"
                        }
                    ],
                    "tabString": "",
                    "widowOrphanControl": false,
                    "keepWithNext":false,
                    "keeplinestogether":false,
                    "matchedKeys": []
                }
            ]""";

}
