<h1>Gerenciador de Tarefas</h1>
    <h3>Task Manager feito em Java com a API JDBC.</h3>
    <p>Versão do Java: openjdk version "17.0.8" 2023-07-18 LTS</p>
    <h2> → Pacotes e Classes </h2>
    <h3>#db</h3>
    <p><strong>DbException</strong></p>
    <p>Classe que faz o tratamento de exceções personalizado para o acesso ao banco de dados. A mensagem que é recebida na exceção é transmitida para a super classe (RuntimeException).</p>
    <p><strong>DB</strong></p>
    <p>Esta classe possui:</p>
    <ul>
        <li>O metodo que faz a leitura das informaçoes do banco de dados (url, user, password).</li>
        <li>Metodos estaticos para conectar e desconectar com o banco de dados. </li>
        <li>Métodos de fechamento das funçoes PreparedStatement e ResultSet.</li>
    </ul>
    <h3>#db</h3>
    <p>Este pacote possui apenas a interface TaskDao, que irá possuir as assinaturas das funções de CRUD.</p>
    <h3>#dao.implement</h3>
    <p>Neste pacote está a classe TaskDaoJDBC que faz a implementação das funçoes de CRUD..</p>
    <h2>→ Como utilizar o sistema em sua maquina:</h2>
    <p>Voce vai precisar:</p>
    <ol>
        <li>Instalar o MySQL  Workbench.</li>
        <li>Rodar o script do Banco de Dados.</li>
        <li>Alterar os endereços no arquivo txt do projeto (arquivos de conexao com o BD).</li>
    </ol>
