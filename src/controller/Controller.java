package controller;

import dao.RankingDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Model;
import sample.DataToExcel;

import java.lang.reflect.Array;
import java.util.*;

import static sample.DataToExcel.exportExcelPaper;

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
    private TextField idTextField;
    @FXML
    private CheckBox cCheckBox;
    @FXML
    private CheckBox dCheckBox;
    @FXML
    private RadioButton inRadioButton;
    @FXML
    private RadioButton rankRadioButton;

    public void do_subButton_event(ActionEvent event) {
        double a;
        double b;
        double g;
        double h;
        String name = nameTextField.getText();
        int id = Integer.parseInt(idTextField.getText());
        //double i, e, f;
        Model model = new Model();
        RankingDao rankingDao = new RankingDao();
        int c = 0, d = 0, num = 0;

        if (cCheckBox.isSelected()) c = 1;
        if (dCheckBox.isSelected()) d = 1;

        if (inRadioButton.isSelected()) {
            a = Double.parseDouble(aTextField.getText());
            b = Double.parseDouble(bTextField.getText());
            g = Double.parseDouble(gTextField.getText());
            h = Double.parseDouble(hTextField.getText());

            model.setA(a);
            model.setB(b);
            model.setG(g);
            model.setH(h);
            model.setName(name);
            model.setId(id);
            model.setC(c);
            model.setD(d);

            num = rankingDao.addRank(model);

            if (num == 1) System.out.println("Input successfully!");
            else System.out.println("Input failed!");
        }

        if (rankRadioButton.isSelected()) {
            model.setId(id);
            model.setC(c);
            model.setD(d);

            num = rankingDao.updateRank(model);
            if (num == 1) System.out.println("Judging success!");
            else System.out.println("Judging failed!");
        }
    }

    public void do_rankButton_event(ActionEvent event) {
        RankingDao rankingDao = new RankingDao();
        List<Model> Rlist = rankingDao.doRank();
        Model model = new Model();
        double a, b, g, h, i, bMax = 0.0, baMax = 0.0;
        int c, d, id;
        int size = Rlist.size();

        for (int k = 0; k < size; k++) {
            b = Rlist.get(k).getB();
            a = Rlist.get(k).getA();
            c = Rlist.get(k).getC();
            d = Rlist.get(k).getD();
            double ba = (b / a);

            if (b > bMax && c == 1 && d == 1)
                bMax = b;

            if (ba > baMax && c == 1 && d == 1)
                baMax = ba;
        }

/*        for (int k=0;k<size;k++){
            double ba = (Rlist.get(k).getB())/(Rlist.get(k).getA());
            if (ba > baMax && Rlist.get(k).getC() == 1 && Rlist.get(k).getD() == 1)
                baMax = ba;
        }*/

        for (int m = 0; m < size; m++) {
            c = Rlist.get(m).getC();
            d = Rlist.get(m).getD();
            a = Rlist.get(m).getA();
            b = Rlist.get(m).getB();
            g = Rlist.get(m).getG();
            h = Rlist.get(m).getH();
            id = Rlist.get(m).getId();
            double e, f;

            if (c == 0)
                e = 0;
            else
                e = ((b / a) / baMax) * 50;

            if (d == 0)
                f = 0;
            else
                f = (b / bMax) * 30;

            i = e + f + g + h;

            model.setId(id);
            model.setI(i);

            int num = rankingDao.addI(model);
            if(num == 1)
            System.out.println("The total score of the "+id+" group is calculated successfully");
        }
        System.out.println("");
        System.out.println("(b/a)Max is "+baMax);
        System.out.println("bMax is "+bMax);
        System.out.println("");
        Rlist = rankingDao.doRank();
        //Collections.sort(Rlist);

        Collections.sort(Rlist, new Comparator<Model>() {
            @Override
            public int compare(Model o1, Model o2) {
                return new Double(o1.getI()).compareTo(new Double(o2.getI()));
            }
        });

        Collections.reverse(Rlist);
        exportExcelPaper(Rlist);
        System.out.println("Ranking completed!");
        //System.out.println(Rlist.toString());


/*      String name = Rlist.toString();
        System.out.println(name);
        double a = Rlist.get(1).getA();
        System.out.println(a);
        teamTableColumn.setCellValueFactory(new PropertyValueFactory("name"));*/

    }

    public void do_delButton_event(ActionEvent event) {
        RankingDao rankingDao = new RankingDao();
        Model model = new Model();
        int id = Integer.parseInt(idTextField.getText());
        int num = rankingDao.delRank(id);
        if (num == 1) System.out.println(id+" group deleted successfully");
    }

}