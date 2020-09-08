package lk.nnj.rms.fx.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.nnj.rms.fx.model.User;

public class ViewFromController {

    @FXML
    private Label lb_id1;

    @FXML
    private Label lb_id2;

    void setDetails(User user){
        lb_id1.setText(user.getName());
        lb_id2.setText(user.getPwd());

    };
}
