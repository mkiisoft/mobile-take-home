package com.guestlogix.marianozorrilla.ui.character.presenter;

import android.content.Context;

import com.guestlogix.marianozorrilla.data.database.DatabaseAccess;
import com.guestlogix.marianozorrilla.ui.presenter.BaseFragmentPresenter;

public class CharacterFragmentPresenter extends BaseFragmentPresenter<CharacterFragmentView> {

    // DATABASE
    private DatabaseAccess database;

    public CharacterFragmentPresenter(CharacterFragmentView fragmentView, Context context) {
        super(fragmentView);
        database = new DatabaseAccess(context);
    }

    public void init(int id) {
        database.getCharacter(id, characterItem -> fragmentView.setCharacterContent(characterItem));
    }
}
