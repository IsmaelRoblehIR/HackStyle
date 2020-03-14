package hackstyle.scripts.actions;

import hackstyle.scripts.ScriptAction;
import hackstyle.scripts.State;

public class EndLoopAction implements ScriptAction {

    @Override
    public void act(State state) {
        final int thisIndex = state.getScriptActions().indexOf(this);
        boolean found = false;

        for (int i = thisIndex + 1; !found && i < state.getScriptActions().size(); i++) {
            final ScriptAction scriptAction = state.getScriptActions().get(i);
            if (scriptAction instanceof EndLoopAction) {
                state.setScriptIndex(i);
                found = true;
            }
        }
    }
}
