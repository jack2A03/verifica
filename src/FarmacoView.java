import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FarmacoView extends JFrame{

    private Farmaco farmaco = null;

    private JLabel lblId = new JLabel("Id: ");
    private JTextField txtNomeGenerico = new JTextField(10);
    private JTextField txtNomeFarmaco = new JTextField(10);
    private JTextField txtIndicazioni = new JTextField(10);
    private JTextField txtDitta = new JTextField(10);
    private JTextField txtQuantita = new JTextField(10);
    private JTextField txtScaffale = new JTextField(10);
    private JTextField txtCassetto = new JTextField(10);

    public FarmacoView(Farmaco farmaco){
        this.farmaco = farmaco;
        setTitle("Farmaco");
        setLocationRelativeTo(null);
        setSize(400,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        populateFields();
        initUi();

        setVisible(true);
    }

    public void initUi(){
        JPanel pnl = new JPanel(new GridLayout(7,1));
        pnl.add(lblId);
        JPanel pnlNomeGenerico = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlNomeGenerico.add(new JLabel("Nome generico: "));
        pnlNomeGenerico.add(txtNomeGenerico);
        JPanel pnlNomeFarmaco = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlNomeFarmaco.add(new JLabel("Nome Farmaco: "));
        pnlNomeFarmaco.add(txtNomeFarmaco);
        JPanel pnlIndicazioni = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlIndicazioni.add(new JLabel("Indicazioni: "));
        pnlIndicazioni.add(txtIndicazioni);
        JPanel pnlDitta = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlDitta.add(new JLabel("Ditta: "));
        pnlDitta.add(txtDitta);
        JPanel pnlQuantita = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlQuantita.add(new JLabel("Quantità: "));
        pnlQuantita.add(txtQuantita);
        JPanel pnlScaffale = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlScaffale.add(new JLabel("Scaffale: "));
        pnlScaffale.add(txtScaffale);
        pnlScaffale.add(new JLabel("\t Cassetto: "));
        pnlScaffale.add(txtCassetto);
        pnl.add(pnlNomeGenerico);
        pnl.add(pnlNomeFarmaco);
        pnl.add(pnlIndicazioni);
        pnl.add(pnlDitta);
        pnl.add(pnlQuantita);
        pnl.add(pnlScaffale);
        JButton btnSalva = new JButton("Salva");
        btnSalva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FarmacoDAO.update(farmaco);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        pnl.add(btnSalva);
        add(pnl);

    }

    public void populateFields(){
        lblId.setText("Id: "+farmaco.getId());
        txtNomeGenerico.setText(farmaco.getNomeGenerico());
        txtNomeFarmaco.setText(farmaco.getNomeFarmaco());
        txtIndicazioni.setText(farmaco.getIndicazioni());
        txtDitta.setText(farmaco.getDitta());
        txtQuantita.setText(farmaco.getQuantita()+"");
        txtScaffale.setText(farmaco.getScaffale()+"");
        txtCassetto.setText(farmaco.getCassetto()+"");
    }


    public static void main(String[] args) {
        FarmacoView fw = new FarmacoView(new Farmaco(0,"palle","nullo","morte","combe&co",60,10,20));
    }

}
