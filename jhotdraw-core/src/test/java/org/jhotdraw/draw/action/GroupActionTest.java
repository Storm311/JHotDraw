package org.jhotdraw.draw.action;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.CompositeFigure;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.*;

public class GroupActionTest {

    private GroupAction groupAction;
    private DrawingEditor editor;
    private DrawingView view;

    @BeforeMethod
    public void setUp() {
        editor = mock(DrawingEditor.class);
        view = mock(DrawingView.class);

        groupAction = spy(new GroupAction(editor));
        doReturn(view).when(editor).getActiveView();
    }
    @Test
    public void testCanGroup_TwoSelectedFigures_ReturnsTrue() {
        when(view.getSelectionCount()).thenReturn(2);
        assertTrue(groupAction.canGroup());
    }

    @Test
    public void testCanGroup_OneSelectedFigure_ReturnsFalse() {
        when(view.getSelectionCount()).thenReturn(1);
        assertFalse(groupAction.canGroup());
    }

    @Test
    public void testCanUngroup_TwoSelectedFiguresAndPrototypeIsNull_ReturnsFalse() {
        when(view.getSelectionCount()).thenReturn(2);
        when(view.getSelectedFigures()).thenReturn(null);
        groupAction.prototype = null;

        assertFalse(groupAction.canUngroup());
    }

    @Test
    public void testCanUngroup_TwoSelectedFiguresAndPrototypeIsNotNull_ReturnsTrue() {
        CompositeFigure figOne = mock(CompositeFigure.class);
        CompositeFigure figTwo = mock(CompositeFigure.class);

        when(view.getSelectionCount()).thenReturn(1);
        when(view.getSelectedFigures()).thenReturn(new HashSet<>(Arrays.asList(figOne, figTwo)));
        groupAction.prototype = figOne;

        assertTrue(groupAction.canUngroup());
    }
}
