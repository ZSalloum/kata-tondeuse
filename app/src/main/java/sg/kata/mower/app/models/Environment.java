package sg.kata.mower.app.models;

import sg.kata.mower.core.models.IEnvironment;
import sg.kata.mower.core.models.IGridLawn;

public class Environment implements IEnvironment {
    private IGridLawn gridLawn;

    public IGridLawn getGridLawn(){
        return gridLawn;
    }

    public void setGridLaw(IGridLawn gridLawn){
        this.gridLawn = gridLawn;
    }
}
