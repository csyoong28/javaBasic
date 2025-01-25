package hyetch;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ACM27605 {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\soonyoong.chia\\OneDrive\\Desktop\\temp\\ACM-27605-alpha.txt");
        String insertStatement = "INSERT into tb_campaign_white_list_user (create_time, user_id, campaign_id, `comment`, include_direct_levels, operator_id)\n" +
                "values";

        List<String> insertStatements = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path)){
            insertStatements.add(insertStatement);
            lines.forEach( x -> {
                String values = "(now(), " + x + ", 19, 'ACM-27605 GCN - data patch whitelist for existing user', 0, 0),";
//                System.out.println(values);
                insertStatements.add(values);
            });
            Files.write(Paths.get("C:\\Users\\soonyoong.chia\\OneDrive\\Desktop\\temp\\ACM-27605-alpha-insert.txt"), insertStatements);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void black() {
        Path path = Paths.get("C:\\Users\\soonyoong.chia\\OneDrive\\Desktop\\temp\\ACM-27605-alpha.txt");

        String insertStatement = "INSERT INTO tb_campaign_blacked_user (user_id, `comment`, create_time, campaign_id, operator_id, include_direct_levels)\n" +
                "values";

        List<String> insertStatements = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path)){
            insertStatements.add(insertStatement);
            lines.forEach( x -> {
                String values = "("+ x + ", 'ACM-27605 GCN - data patch block for RDP user', now(), 5036, 0, 0),";
//                System.out.println(values);
                insertStatements.add(values);
            });
            Files.write(Paths.get("C:\\Users\\soonyoong.chia\\OneDrive\\Desktop\\temp\\ACM-27605-alpha-black-insert.txt"), insertStatements);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
