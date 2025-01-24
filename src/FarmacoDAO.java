import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FarmacoDAO extends GenericDAO {

    public static Object readById(int idFarmaco)  throws SQLException{
        GenericDAO.connect();
        String sql = "SELECT farmaco.id, nomegenerico, nomefarmaco, indicazioni, ditta, quantita, scaffale, cassetti.cassetto AS casset FROM farmaco, cassetti WHERE farmaco.id = ? AND cassetti.id=farmaco.cassetto;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,idFarmaco);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            Farmaco farmaco = new Farmaco(idFarmaco,
                    resultSet.getString("nomegenerico"),
                    resultSet.getString("nomefarmaco"),
                    resultSet.getString("indicazioni"),
                    resultSet.getString("ditta"),
                    resultSet.getInt("quantita"));
            farmaco.setScaffale(resultSet.getInt("scaffale"));
            farmaco.setCassetto(resultSet.getInt("casset"));
            disconnect();
            return farmaco;
        }
        disconnect();
        return null;
    }

    public static boolean delete(Object object) throws SQLException{

        connect();

        String sql = "DELETE FROM farmaco, cassetti WHERE farmaco.id=? AND cassetti.id=farmaco.cassetto;";


       if(result>0){
            GenericDAO.disconnect();
            return false;
        }
        GenericDAO.disconnect();
        return true;
    }
    }

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
            String sqlCassetti = "SELECT scaffale,cassetto FROM cassetti WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sqlCassetti);
            ps.setInt(1,resultSet.getInt("cassetto"));
            ResultSet resultSetCassetti = ps.executeQuery();
            farmaco.setScaffale(resultSetCassetti.getInt("scaffale"));
            farmaco.setCassetto(resultSetCassetti.getInt("cassetto"));
            farmaci.add(farmaco);
        }
        return farmaci;
    }

    public static boolean update(Object object) throws SQLException {

        GenericDAO.connect();

        Farmaco farmaco = (Farmaco) object;

        String sql1 = "UPDATE farmaco SET nomegenerico=?, nomefarmaco=?, indicazioni=?, ditta=?, quantita=? WHERE farmaco.id=?";
        String sql2 = "UPDATE cassetti SET scaffale=?, cassetto=? WHERE id=?";
        String sql3 = "SELECT cassetto FROM farmaco WHERE id=?;";

        PreparedStatement ps = connection.prepareStatement(sql1);
        PreparedStatement ps1 = connection.prepareStatement(sql2);
        PreparedStatement ps2 = connection.prepareStatement(sql3);

        ps.setString(1,farmaco.getNomeGenerico());
        ps.setString(2,farmaco.getNomeFarmaco());
        ps.setString(3,farmaco.getIndicazioni());
        ps.setString(4,farmaco.getDitta());
        ps.setInt(5,farmaco.getQuantita());
        ps.setInt(6,farmaco.getId());

        ps2.setInt(1,farmaco.getId());

        ResultSet resultSet = ps2.executeQuery();

        ps1.setInt(1,farmaco.getScaffale());
        ps1.setInt(2,farmaco.getCassetto());
        ps1.setInt(3,resultSet.getInt("cassetto"));

        int result1 =ps1.executeUpdate();

        int result = ps.executeUpdate();
        if(result>0 && result1>0){
            GenericDAO.disconnect();
            return false;
        }
        GenericDAO.disconnect();
        return true;
    }
}
