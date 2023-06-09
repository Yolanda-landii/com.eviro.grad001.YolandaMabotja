package com.eviro.assessment.grad001.yolandamabotja.controllers;

public class ResourceNotFoundException extends Throwable {
    public ResourceNotFoundException(String fileNotFound) {
        super(fileNotFound);
    }
}
