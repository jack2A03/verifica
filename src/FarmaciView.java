import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class FarmaciView extends JFrame implements ActionListener {

    private ArrayList<Farmaco> farmaciRicercati = new ArrayList<>();

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

    private JButton btnAggiungi = null;
    private JButton btnElimina = null;
    private JButton btnDettaglio = null;

    public FarmaciView() throws SQLException {

        this.farmaci = casting(FarmacoDAO.readAll());
        this.farmaciRicercati = farmaci;

        setSize(500, 400);
        setLocationRelativeTo(null);
        setTitle("Ricerca farmaco");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();

        setVisible(true);

    }

    private ArrayList<Farmaco> casting(ArrayList<Object> objects) {
        ArrayList<Farmaco> farmacos = new ArrayList<>();
        for (Object object : objects){
            farmacos.add((Farmaco) object);
        }
        return farmacos;
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
        populate();
        add(scrollPane,BorderLayout.CENTER);

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAggiungi = new JButton("Aggiungi");
        btnAggiungi.addActionListener(this);
        btnElimina = new JButton("Elimina");
        btnElimina.addActionListener(this);
        btnDettaglio = new JButton("Dettaglio");
        btnDettaglio.addActionListener(this);
        pnlBtn.add(btnDettaglio);
        pnlBtn.add(btnAggiungi);
        pnlBtn.add(btnElimina);

        add(pnlBtn,BorderLayout.SOUTH);


    }

    private void populate() {
        try {
            farmaci =casting(FarmacoDAO.readAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DefaultTableModel model = new DefaultTableModel();

        String [] cols = new String[]{"Id","Generico","Nome","Indicazioni","Ditta","Q","Scaffale","Cassetto"};
        for (String col : cols){
            model.addColumn(col);
        }

        for (Farmaco farmaco : farmaci){
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
            populate();
        } else if (e.getSource() == btnDettaglio){
            dettaglio();
            populate();
        } else if (e.getSource() == btnElimina) {

        }

    }

    private void dettaglio() {

        int row = tblFarmaci.getSelectedRow();
        int idFarmaco = Integer.parseInt(tblFarmaci.getValueAt(row,0)+"");

        try {
            FarmacoView fw = new FarmacoView((Farmaco) FarmacoDAO.readById(idFarmaco));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void ricerca() {
        farmaciRicercati = new ArrayList<>();
        for (Farmaco farmaco : farmaci){
            if (txtIndicazione.getText().isEmpty() && farmaco.getNomeFarmaco().toLowerCase().contains(txtNomeGenerico.getText().toLowerCase())){
                    farmaciRicercati.add(farmaco);
            } else if (txtNomeGenerico.getText().isEmpty() && farmaco.getIndicazioni().toLowerCase().contains(txtIndicazione.getText().toLowerCase())) {
                    farmaciRicercati.add(farmaco);
            } else if (farmaco.getNomeGenerico().toLowerCase().contains(txtNomeGenerico.getText().toLowerCase()) && farmaco.getIndicazioni().toLowerCase().contains(txtIndicazione.getText().toLowerCase())){
                farmaciRicercati.add(farmaco);
            }
        }
        populate();
    }

    public static void main(String[] args) {
        ArrayList<Farmaco> farmacos = new ArrayList<>();
        farmacos.add(new Farmaco(0,"acido acetilsalicilico","aspirina","cefalea","Bayer",567,10,1));
        try {
            FarmaciView fw = new FarmaciView();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
