import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
    public static Team team;

    public static final String EXIT = "0";
    public static final String BASIC_REPORT = "1";
    public static final String DETAILED_REPORT = "2";

    public static void main(String[] args) {
        setTeamData();
        setPlayersToTeam();
        generateReport();
    }

    public static void setTeamData() {
        team = new Team();
        team.setName(JOptionPane.showInputDialog(
                null,
                "Ingrese el nombre del equipo:",
                "Nombre equipo",
                JOptionPane.PLAIN_MESSAGE));
        team.setStadiumName(JOptionPane.showInputDialog(
                null,
                "Ingrese el nombre del estadio del equipo:",
                "Nombre estado del equipo",
                JOptionPane.PLAIN_MESSAGE));

        try {
            int amountTitles = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Ingrese la cantidad de títulos del equipo:",
                    "Títulos del equipo",
                    JOptionPane.PLAIN_MESSAGE));
            team.setAmountTitles(amountTitles);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Campo cantidad de títulos debe ser numérico"
            );
        }
    }

    public static void setPlayersToTeam() {
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setAlwaysOnTop(true);

        int amountPlayers = 0;
        // Cantidad de jugadores debe ser mayor a 0
        while (amountPlayers <= 0) {
            try {
                amountPlayers = Integer.parseInt(JOptionPane.showInputDialog(
                        null,
                        "Ingresar cantidad de jugadores del equipo:",
                        "Cantidad de jugadores",
                        JOptionPane.PLAIN_MESSAGE));

                if (amountPlayers <= 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "El campo cantidad jugadores debe ser mayor que cero");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "El campo cantidad jugadores solo puede contener números");
            }
        }

        // Lista de jugadores del equipo
        List<Player> playersList = new ArrayList<Player>();

        // Crear formulario por cada usuario
        for (int i = 0; i < amountPlayers; i++) {
            int playerNum = i + 1;
            Player player = new Player();

            // Agregar nombre
            player.setName(JOptionPane.showInputDialog(
                    null,
                    "Ingrese el nombre del jugador:",
                    "Jugador #" + playerNum + " Nombre Jugador", JOptionPane.PLAIN_MESSAGE));

            // Agregar tipo de jugador
            Object selectedPlayerType;
            selectedPlayerType = JOptionPane.showInputDialog(
                    frame,
                    "Seleccione el tipo de jugador para el jugador: " + player.getName(),
                    "Jugador #" + playerNum + " Tipo Jugador",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    PlayerType.values(),
                    PlayerType.values()[0]);
            player.setPlayerType(PlayerType.valueOf(selectedPlayerType.toString()));

            // Agregar posicion de jugador
            Object selectedPosition;
            selectedPosition = JOptionPane.showInputDialog(
                    frame,
                    "Seleccione la posición en la que juega el jugador: " + player.getName(),
                    "Jugador #" + playerNum + " Tipo Posición Jugador",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    PositionPlayerType.values(),
                    PositionPlayerType.values()[0]);

            player.setPositionPlayerType(PositionPlayerType.valueOf(selectedPosition.toString()));
            playersList.add(player);
        }

        team.setPlayers(playersList);
    }

    public static void generateReport() {
        String report = "";

        // Permitir al usuario intentar varias veces
        while (true) {
            String options = "¿Que tipo de reporte desea generar? (Ingrese el digito correspondiente)\r\n" +
                    "1 - Reporte básico.\r\n" +
                    "2 - Reporte detallado.\r\n" +
                    "0 - Salir";

            report = JOptionPane.showInputDialog(
                    null,
                    options,
                    "Reportes",
                    JOptionPane.PLAIN_MESSAGE);

            if (report == null) System.exit(0);

            switch (report) {
                case BASIC_REPORT:
                    JOptionPane.showMessageDialog(
                            null,
                            getTableBasicReportHTML(),
                            "Reporte Básico",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case DETAILED_REPORT:
                    JOptionPane.showMessageDialog(
                            null,
                            getTableDetailedReportHTML(),
                            "Reporte detallado",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case EXIT:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    public static String getTableDetailedReportHTML() {
        StringBuilder tableHTML = new StringBuilder()
                .append("<html><body>")
                .append("<p><strong>Nombre Equipo: </strong>")
                .append(team.getName())
                .append("<p>")
                .append("<table cellspacing='3' border='1' bgcolor='#ffffff'>")
                .append("<caption>Jugadores del equipo: ")
                .append(team.getName())
                .append("</caption>")
                .append("<thead><tr>");

        String[] titles = {"Nombre jugador", "Tipo jugador", "Posición jugador"};
        for (String title : titles) {
            tableHTML
                    .append("<td>")
                    .append(title)
                    .append("</td>");
        }

        tableHTML.append("</tr></thead><tbody>");

        for (Player player : team.getPlayers()) {
            tableHTML.append(player.toString());
        }
        tableHTML.append("</tbody></table></body></html>");

        return tableHTML.toString();
    }


    public static String getTableBasicReportHTML() {
        StringBuilder tableHTML = new StringBuilder()
                .append("<html><body>")
                .append("<table cellspacing='3' border='1' bgcolor='#ffffff'>")
                .append("<thead><tr>");

        String[] titles = {"Nombre del equipo", "Títulos ganados", "Cantidad Jugadores"};
        for (String title : titles) {
            tableHTML
                    .append("<td>")
                    .append(title)
                    .append("</td>");
        }

        tableHTML.append("</tr></thead><tbody>")
                .append("<tr>")
                .append("<td>")
                .append(team.getName())
                .append("</td>")
                .append("<td>")
                .append(team.getAmountTitles())
                .append("</td>")
                .append("<td>")
                .append(team.getPlayers().size())
                .append("</td>")
                .append("</tr>");
        tableHTML.append("</tbody></table></body></html>");

        return tableHTML.toString();
    }
}
