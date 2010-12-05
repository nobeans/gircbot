package org.jggug.kobo.gircbot.core;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class TimeMonitorTest {

    private TimeMonitor timeMonitor;
    private TimeEventListener listenerMock;
    private PrimaryMonitor primaryMonitorMock;

    @Before
    public void before() {
        listenerMock = mock(TimeEventListener.class);

        primaryMonitorMock = mock(PrimaryMonitor.class);
        when(primaryMonitorMock.isPrimaryGlobally()).thenReturn(true);

        // Setup SUT
        timeMonitor = new TimeMonitor();
        timeMonitor.addTimeEventListener(listenerMock);
        timeMonitor.setPrimaryMonitor(primaryMonitorMock);
    }

    @Test
    public void start() throws Exception {
        // Exercise
        timeMonitor.start();
        Thread.sleep(3000);
        // Verify
        verify(primaryMonitorMock, atLeast(2)).isPrimaryGlobally();
        verify(listenerMock, atLeast(2)).invoke((Date) anyObject());
    }

    @Test
    public void start_ifMultipleInvoking_thenThrowingIllegalStateException() throws Exception {
        timeMonitor.start();
        try {
            timeMonitor.start();
            fail();
        } catch (IllegalStateException e) {
        }
    }
}
