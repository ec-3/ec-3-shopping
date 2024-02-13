package com.peaq.ec3.shopp.config;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Comparator;
import java.util.Map;

public class Ec3PathMatcher implements PathMatcher {

    AntPathMatcher antPathMatcher;

    @Override
    public boolean isPattern(String s) {
        return antPathMatcher.isPattern(s);
    }

    @Override
    public boolean match(String s, String s1) {
        return false;
    }

    @Override
    public boolean matchStart(String s, String s1) {
        return antPathMatcher.matchStart(s,s1);
    }

    @Override
    public String extractPathWithinPattern(String s, String s1) {
        return null;
    }

    @Override
    public Map<String, String> extractUriTemplateVariables(String s, String s1) {
        return null;
    }

    @Override
    public Comparator<String> getPatternComparator(String s) {
        return null;
    }

    @Override
    public String combine(String s, String s1) {
        return null;
    }
}
