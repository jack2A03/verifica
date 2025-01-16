import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FarmacoDAO extends GenericDAO {

    public static ArrayList<Object> readAll()  throws SQLException{
        GenericDAO.connect();
        ArrayList<Object> farmaci = new ArrayList<>();
        String sql = "SELECT * FROM farmaco;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Farmaco farmaco = new Farmaco(resultSet.getInt("id"),
                    resultSet.getString("nomegenerico"),resultSet.getString("nomefarmaco"),
                    resultSet.getString("indicazioni"),resultSet.getString("ditta"),resultSet.getInt("quantita"));
            int cassetto = resultSet.getInt("cassetto");
            String sqlCassetti = "SELECT scaffale,cassetto FROM cassetti WHERE id = "+cassetto+";";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSetCassetti = ps.executeQuery();
            farmaco.setScaffale(resultSetCassetti.getInt("scaffale"));
            farmaco.setCassetto(resultSetCassetti.getInt("cassetto"));
            farmaci.add(farmaco);
        }
        return farmaci;
    }
}
