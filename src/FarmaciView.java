import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FarmaciView extends JFrame implements ActionListener {

    private JTextField txtNomeGenerico = null;
    private JTextField txtIndicazione = null;

    private JButton btnCerca = null;
    private JButton btnClear = null;

    private JTable tblFarmaci = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    ArrayList<Farmaco> farmaci = null;

    public FarmaciView(ArrayList<Farmaco> farmaci) {

        this.farmaci = farmaci;

        setSize(500, 400);
        setLocationRelativeTo(null);
        setTitle("Ricerca farmaco");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();

        setVisible(true);

    }

    private void initUI() {

        JPanel pnl = new JPanel(new GridLayout(3, 1));

        JPanel pnlNome = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtNomeGenerico = new JTextField(10);
        //txtNomeGenerico.addActionListener(this);
        pnlNome.add(new JLabel("Nome"));
        pnlNome.add(txtNomeGenerico);
        pnl.add(pnlNome);

        JPanel pnlIndicazioni = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtIndicazione = new JTextField(10);
        //txtIndicazione.addActionListener(this);
        pnlIndicazioni.add(new JLabel("Indicazione"));
        pnlIndicazioni.add(txtIndicazione);
        pnl.add(pnlIndicazioni);

        JPanel pnlBottoni = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCerca = new JButton("Cerca");
        btnCerca.addActionListener(this);
        btnClear = new JButton("Clear");
        btnClear.addActionListener(this);
        pnlBottoni.add(btnCerca);
        pnlBottoni.add(btnClear);
        pnl.add(pnlBottoni);
        add(pnl,BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(tblFarmaci);
        populate(farmaci);
        add(scrollPane,BorderLayout.CENTER);


    }

    private void populate(ArrayList<Farmaco> farmaciRicercati) {

        DefaultTableModel model = new DefaultTableModel();

        String [] cols = new String[]{"Id","Generico","Nome","Indicazioni","Ditta","Q","Scaffale","Cassetto"};
        for (String col : cols){
            model.addColumn(col);
        }

        for (Farmaco farmaco : farmaciRicercati){
            model.addRow(farmaco.toRow());
        }

        tblFarmaci.setModel(model);
        tblFarmaci.getTableHeader().setReorderingAllowed(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // e.getSource() == txtNomeGenerico || e.getSource() == txtIndicazione ||
        if (e.getSource() == btnCerca){
            ricerca();
        } else if (e.getSource() == btnClear) {
            txtNomeGenerico.setText("");
            txtIndicazione.setText("");
            populate(farmaci);
        }

    }

    private void ricerca() {
        ArrayList<Farmaco> farmaciRicerca = new ArrayList<>();
        for (Farmaco farmaco : farmaci){
            if (txtIndicazione.getText().equals("")){
                if(txtNomeGenerico.getText().equals(farmaco.getNomeFarmaco())){
                    farmaciRicerca.add(farmaco);
                }
            } else if (txtNomeGenerico.getText().equals("")) {
                if(farmaco.getIndicazioni().contains(txtIndicazione.getText())){
                    farmaciRicerca.add(farmaco);
                }
            } else if (farmaco.getNomeGenerico().equals(txtNomeGenerico.getText()) && farmaco.getIndicazioni().contains(txtIndicazione.getText())){
                farmaciRicerca.add(farmaco);
            }
        }
        populate(farmaciRicerca);
    }

    public static void main(String[] args) {
        ArrayList<Farmaco> farmacos = new ArrayList<>();
        farmacos.add(new Farmaco(0,"acido acetilsalicilico","aspirina","cefalea","Bayer",567,10,1));
        FarmaciView fw = new FarmaciView(farmacos);
    }
}
