package controller;

import dao.RankingDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Model;

import java.util.List;

public class Controller {
    @FXML
    private TextField aTextField;
    @FXML
    private TextField bTextField;
    @FXML
    private TextField gTextField;
    @FXML
    private TextField hTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TableColumn rankTableColumn;
    @FXML
    private TableColumn teamTableColumn;
    @FXML
    private TableColumn scoreTableColumn;
    @FXML
    private CheckBox cCheckBox;
    @FXML
    private CheckBox dCheckBox;
    @FXML
    private TableView rankingTableView;

    public void do_subButton_event(ActionEvent event) {
        double a = Double.parseDouble(aTextField.getText());
        double b = Double.parseDouble(bTextField.getText());
        double g = Double.parseDouble(gTextField.getText());
        double h = Double.parseDouble(hTextField.getText());
        String name = nameTextField.getText();
        double i, e, f;
        Model model = new Model();
        RankingDao rankingDao = new RankingDao();
        int c = 0,d = 0;

        if (cCheckBox.isSelected()) c = 1;
        if (dCheckBox.isSelected()) d = 1;

        model.setA(a);
        model.setB(b);
        model.setG(g);
        model.setH(h);
        model.setName(name);
        model.setC(c);
        model.setD(d);

        int num = rankingDao.addRank(model);

        if (num == 1) System.out.println("成");
        else System.out.println("败");

    }

    public void do_rankButton_event(ActionEvent event){
        RankingDao rankingDao = new RankingDao();
        List<Model> Rlist = rankingDao.doRank();
        double a,b,g,h,i;
        int c,d;
        TableColumn<Model,String> team = new TableColumn<>();


/*      String name = Rlist.toString();
        System.out.println(name);
        double a = Rlist.get(1).getA();
        System.out.println(a);
        teamTableColumn.setCellValueFactory(new PropertyValueFactory("name"));*/



    }

    public void do_delButton_event(ActionEvent event) {

    }

}