package com.example.projetobabypet.util;

import android.graphics.Color;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

import com.example.projetobabypet.model.Compromisso;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

public class DecorarCalendario implements DayViewDecorator {
    private final int highlightColor;
    private final HashSet<CalendarDay> eventDates;

    public DecorarCalendario(int color, Collection<CalendarDay> dates) {
        highlightColor = color;
        eventDates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return eventDates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.WHITE));
        view.addSpan(new BackgroundColorSpan(highlightColor));
    }
}