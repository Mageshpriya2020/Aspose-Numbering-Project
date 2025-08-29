package com.numbering.demo.NUM_PACKAGE;

import com.aspose.words.Style;

public class ListInfo {
    private int listId;
    private Style style;

    public ListInfo(int listId, Style style) {
        this.listId = listId;
        this.style = style;
    }

    public int getListId() {
        return listId;
    }

    public Style getStyle() {
        return style;
    }
}

