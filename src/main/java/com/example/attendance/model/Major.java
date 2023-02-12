package com.example.attendance.model;

public enum Major {
    EE("Electrical engineering"),
    EM("Electromechanical engineering"),
    AE("Automation engineering"),
    CS("Computer science");

    public final String value;

    private Major(String value) {
        this.value = value;
    }

    public static Major getMajor(String majorName) {
        return Major.valueOf(majorName.toUpperCase());
    }
}
