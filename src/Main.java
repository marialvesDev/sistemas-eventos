
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Evento> eventos = new ArrayList<>();
        ArrayList<Evento> eventosConfirmados = new ArrayList<>();

        // O QUE ESSE CODIGO FAZ?
        //FileReader → abre o arquivo
        // Scanner → lê linha por linha
        //split(";") → separa os dados
        //cria Evento → igual eu fiz
        //adiciona na lista

        try {
            FileReader reader = new FileReader("events.data");
            Scanner leitor = new Scanner(reader);

            while (leitor.hasNextLine()) {

                String linha = leitor.nextLine();
                String[] partes = linha.split(";");

                String nomeEvento = partes[0];
                String endereco = partes[1];
                String categoria = partes[2];
                LocalDateTime horario = LocalDateTime.parse(partes[3]);
                String descricao = partes[4];

                Evento evento = new Evento(nomeEvento, endereco, categoria, horario, descricao);
                eventos.add(evento);
            }

            leitor.close();

        } catch (Exception e) {
            System.out.println("Nenhum arquivo encontrado.");
        }

        // CADASTRO DO USUARIO
        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();

        System.out.println("Digite seu email:");
        String email = scanner.nextLine();

        System.out.println("Digite seu telefone:");
        String telefone = scanner.nextLine();

        System.out.println("Cidade: Lauro de Freitas");
        String cidade = "Lauro de Freitas";

        Usuario usuario = new Usuario(nome, email, telefone);

        System.out.println("\n--- DADOS ---");
        System.out.println("Nome: " + usuario.nome);
        System.out.println("Email: " + usuario.email);
        System.out.println("Telefone: " + usuario.telefone);
        System.out.println("Cidade: " + cidade);

        int opcao;

        do {
            System.out.println("\n1 - Criar evento");
            System.out.println("2 - Ver eventos");
            System.out.println("3 - Participar de evento");
            System.out.println("4 - Meus Eventos");
            System.out.println("5 - Cancelar Inscrição");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(scanner.nextLine());

            // CRIAR EVENTO
            if (opcao == 1) {

                System.out.println("\nDigite o nome do evento:");
                String nomeDoEvento = scanner.nextLine();

                System.out.println("Escolha a categoria:");
                System.out.println("1 - Festa");
                System.out.println("2 - Show");
                System.out.println("3 - Esportivo");
                System.out.println("4 - Teatro");
                System.out.println("5 - Conferência");
                System.out.println("6 - Exposição");
                System.out.println("7 - Comunitário");

                int escolhaCat = Integer.parseInt(scanner.nextLine());
                String categoria;

                switch (escolhaCat) {
                    case 1:
                        categoria = "Festa";
                        break;
                    case 2:
                        categoria = "Show";
                        break;
                    case 3:
                        categoria = "Esportivo";
                        break;
                    case 4:
                        categoria = "Teatro";
                        break;
                    case 5:
                        categoria = "Conferência";
                        break;
                    case 6:
                        categoria = "Exposição";
                        break;
                    case 7:
                        categoria = "Comunitário";
                        break;
                    default:
                        categoria = "Festa";
                        System.out.println("Categoria padrão: Festa");
                }

                System.out.println("Digite o endereço:");
                String endereco = scanner.nextLine();

                System.out.println("Digite o horário (AAAA-MM-DD HH:MM):");
                String horarioStr = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime horario = LocalDateTime.parse(horarioStr, formatter);

                System.out.println("Digite a descrição:");
                String descricao = scanner.nextLine();

                Evento evento = new Evento(nomeDoEvento, endereco, categoria, horario, descricao);
                eventos.add(evento);

                // SALVAR NO ARQUIVO
                try {
                    FileWriter writer = new FileWriter("events.data", true);

                    writer.write(evento.nomeDoEvento + ";" +
                            evento.endereco + ";" +
                            evento.categoria + ";" +
                            evento.horario + ";" +
                            evento.descricao + "\n");

                    writer.close();

                } catch (Exception e) {
                    System.out.println("Erro ao salvar.");
                }

                System.out.println("Evento criado com sucesso!");
            }

            // VER EVENTOS


            else if (opcao == 2) {
                eventos.sort((e1, e2) -> e1.horario.compareTo(e2.horario));
                if (eventos.isEmpty()) {
                    System.out.println("Nenhum evento.");
                } else {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    for (int i = 0; i < eventos.size(); i++) {
                        Evento e = eventos.get(i);

                        System.out.println("\n" + (i + 1) + " - " + e.nomeDoEvento);
                        System.out.println("Endereço: " + e.endereco);
                        System.out.println("Categoria: " + e.categoria);
                        System.out.println("Horário: " + e.horario.format(formatter));
                        System.out.println("Descrição: " + e.descricao);
                    }
                }
            }

            // PARTICIPAR
            else if (opcao == 3) {

                if (eventos.isEmpty()) {
                    System.out.println("Nenhum evento disponível.");
                } else {

                    for (int i = 0; i < eventos.size(); i++) {
                        System.out.println((i + 1) + " - " + eventos.get(i).nomeDoEvento);
                    }

                    int escolha = Integer.parseInt(scanner.nextLine());
                    eventosConfirmados.add(eventos.get(escolha - 1));

                    System.out.println("Presença confirmada!");
                }
            }

            // MEUS EVENTOS
            else if (opcao == 4) {

                if (eventosConfirmados.isEmpty()) {
                    System.out.println("Nenhum evento confirmado.");
                } else {

                    for (int i = 0; i < eventosConfirmados.size(); i++) {
                        System.out.println((i + 1) + " - " + eventosConfirmados.get(i).nomeDoEvento);
                    }
                }
            }

            // CANCELAR
            else if (opcao == 5) {

                if (eventosConfirmados.isEmpty()) {
                    System.out.println("Nenhum evento.");
                } else {

                    for (int i = 0; i < eventosConfirmados.size(); i++) {
                        System.out.println((i + 1) + " - " + eventosConfirmados.get(i).nomeDoEvento);
                    }

                    int escolha = Integer.parseInt(scanner.nextLine());
                    eventosConfirmados.remove(escolha - 1);

                    System.out.println("Inscrição cancelada.");
                }
            }

        } while (opcao != 0);

        System.out.println("Programa encerrado.");
    }
}


