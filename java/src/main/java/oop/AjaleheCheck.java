package oop;

import javafx.scene.control.CheckBox;
import oop.objects.Ajaleht;

public class AjaleheCheck extends CheckBox {

    private Ajaleht ajaleht;

    public Ajaleht getAjaleht() {
        return ajaleht;
    }

    public AjaleheCheck(String text, Ajaleht ajaleht) {
        super(text);
        this.ajaleht = ajaleht;
    }

}
