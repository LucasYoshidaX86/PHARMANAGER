package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import dao.MedicamentoDAO;
import model.Medicamento;

public class TelaPrincipal extends JFrame {

    private JPanel panelLateral, panelPrincipal;
    private CardLayout cardLayout;

    public TelaPrincipal() {
        setTitle("Tela Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Painel lateral com opções
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));

        JButton btnAdicionarMedicamento = new JButton("Adicionar Medicamento");
        btnAdicionarMedicamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "AdicionarMedicamento");
            }
        });

        JButton btnListarMedicamentos = new JButton(" Listar Medicamentos   ");
        btnListarMedicamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelPrincipal, "ListarMedicamentos");
            }
        });

        panelLateral.add(btnAdicionarMedicamento);
        panelLateral.add(btnListarMedicamentos);

        // Painel principal com o CardLayout
        panelPrincipal = new JPanel();
        cardLayout = new CardLayout();
        panelPrincipal.setLayout(cardLayout);

        // Tela de Adicionar Medicamento
        JPanel panelAdicionarMedicamento = new JPanel();
        panelAdicionarMedicamento.setLayout(null);

        JLabel lblNome = new JLabel("Nome do Medicamento:");
        lblNome.setBounds(20, 20, 150, 25);
        panelAdicionarMedicamento.add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(180, 20, 150, 25);
        panelAdicionarMedicamento.add(txtNome);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(20, 60, 150, 25);
        panelAdicionarMedicamento.add(lblQuantidade);

        JTextField txtQuantidade = new JTextField();
        txtQuantidade.setBounds(180, 60, 150, 25);
        panelAdicionarMedicamento.add(txtQuantidade);

        JLabel lblDataFabricacao = new JLabel("Data de Fabricação:");
        lblDataFabricacao.setBounds(20, 100, 150, 25);
        panelAdicionarMedicamento.add(lblDataFabricacao);

        JTextField txtDataFabricacao = new JTextField();
        txtDataFabricacao.setBounds(180, 100, 150, 25);
        panelAdicionarMedicamento.add(txtDataFabricacao);

        JLabel lblDataValidade = new JLabel("Data de Validade:");
        lblDataValidade.setBounds(20, 140, 150, 25);
        panelAdicionarMedicamento.add(lblDataValidade);

        JTextField txtDataValidade = new JTextField();
        txtDataValidade.setBounds(180, 140, 150, 25);
        panelAdicionarMedicamento.add(txtDataValidade);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(20, 180, 150, 25);
        panelAdicionarMedicamento.add(lblDescricao);

        JTextArea txtDescricao = new JTextArea();
        txtDescricao.setBounds(180, 180, 150, 60);
        panelAdicionarMedicamento.add(txtDescricao);

        JLabel lblTipo = new JLabel("Tipo de Medicamento:");
        lblTipo.setBounds(20, 260, 150, 25);
        panelAdicionarMedicamento.add(lblTipo);

        String[] tipos = {"Antibiótico", "Analgésico", "Anticoagulante", "Antidepressivo", "Antifungico", "AntiHipertensivo", "AntiInflamatório", "Antiviral", ""};
        JComboBox<String> comboBoxTipo = new JComboBox<>(tipos);
        comboBoxTipo.setBounds(180, 260, 150, 25);
        panelAdicionarMedicamento.add(comboBoxTipo);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(180, 300, 150, 25);
        panelAdicionarMedicamento.add(btnSalvar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = txtNome.getText();
                    String quantidade = txtQuantidade.getText();
                    String dataFabricacao = txtDataFabricacao.getText();
                    String dataValidade = txtDataValidade.getText();
                    String descricao = txtDescricao.getText();
                    String tipo = (String) comboBoxTipo.getSelectedItem();

                    // Validação dos dados
                    if (nome.isEmpty() || quantidade.isEmpty() || dataFabricacao.isEmpty() || dataValidade.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.");
                        return;
                    }

                    // Converter datas para o formato correto
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date fabrica = new Date(sdf.parse(dataFabricacao).getTime());
                    Date validade = new Date(sdf.parse(dataValidade).getTime());

                    // Criar objeto Medicamento
                    Medicamento medicamento = new Medicamento();
                    medicamento.setNome(nome);
                    medicamento.setQuantidade(Integer.parseInt(quantidade));
                    medicamento.setDataFabricacao(fabrica);
                    medicamento.setDataValidade(validade);
                    medicamento.setTipo(tipo);

                    // Salvar no banco de dados
                    MedicamentoDAO dao = new MedicamentoDAO();
                    boolean sucesso = dao.salvar(medicamento);

                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Medicamento adicionado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao adicionar medicamento.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar os dados. Verifique as informações.");
                }
            }
        });

        panelPrincipal.add(panelAdicionarMedicamento, "AdicionarMedicamento");

        // Tela de Listar Medicamentos
        JPanel panelListarMedicamentos = new JPanel();
        panelListarMedicamentos.setLayout(new BorderLayout());

        // Lista de medicamentos
        JList<String> listaMedicamentos = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Preencher lista com medicamentos do banco
        MedicamentoDAO dao = new MedicamentoDAO();
        List<Medicamento> medicamentos = dao.listarTodos(); // Garantindo que seja do tipo List<Medicamento>
        for (Medicamento medicamento : medicamentos) {
            String medicamentoInfo = "Nome: " + medicamento.getNome() +
                                     ", Quantidade: " + medicamento.getQuantidade() +
                                     ", Fabricação: " + new SimpleDateFormat("dd/MM/yyyy").format(medicamento.getDataFabricacao()) +
                                     ", Validade: " + new SimpleDateFormat("dd/MM/yyyy").format(medicamento.getDataValidade());
            listModel.addElement(medicamentoInfo);
        }
        listaMedicamentos.setModel(listModel);

        // Ação ao selecionar um medicamento
        listaMedicamentos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selecionado = listaMedicamentos.getSelectedValue();
                if (selecionado != null) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Deseja excluir o medicamento selecionado?", "Excluir Medicamento", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Encontrar o medicamento correspondente
                        Medicamento medicamentoParaExcluir = medicamentos.stream()
                                .filter(m -> m.getNome().equals(selecionado.split(",")[0].split(":")[1].trim())) // Filtra pelo nome
                                .findFirst()
                                .orElse(null);
                        if (medicamentoParaExcluir != null) {
                            dao.excluir(medicamentoParaExcluir.getId());
                            listModel.removeElement(selecionado);
                            JOptionPane.showMessageDialog(null, "Medicamento excluído com sucesso!");
                        }
                    }
                }
            }
        });

        panelListarMedicamentos.add(new JScrollPane(listaMedicamentos), BorderLayout.CENTER);

        // Botão para voltar ao painel principal
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> cardLayout.show(panelPrincipal, "Vazio"));
        panelListarMedicamentos.add(btnVoltar, BorderLayout.SOUTH);

        panelPrincipal.add(panelListarMedicamentos, "ListarMedicamentos");

        // Exibindo painel vazio ao iniciar
        cardLayout.show(panelPrincipal, "Vazio");

        // Adicionando os painéis ao JFrame
        getContentPane().add(panelLateral, BorderLayout.WEST);
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}




