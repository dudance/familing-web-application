package com.project.project.commons.enums;

public enum InvitationTypeEnum {
    REQUEST("INV_FROM"),
    INVITATION("INV_TO");

    private String type;

    InvitationTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
