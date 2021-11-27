import java.sql.*;
import java.util.ArrayList;

public class DaoCliente {
	private Connection conn;
	private Statement st;
	
	private void conectar(){
		try{
			conn = GerenciadorConexao.pegarConexao();
			st = conn.createStatement();
		}catch(ClassNotFoundException e1){
			System.out.println(e1.getMessage());
		}catch(SQLException e2){
			System.out.println(e2.getMessage());
		}
	}
	
	private void desconectar(){
		try{
			st.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("Erro ao fechar a conexao: " + e.getMessage());
		}
	}
	
	public boolean inserir(Cliente c){
		boolean resultado = false;
		try{
			conectar();
			String comando = "INSERT INTO tb_clientes VALUES (NULL, '"
				+ c.getNome() + "', '" + c.getEmail() + "', '"
				+ c.getDataNascimento() + "', " + c.getTelefone() + ");";
			
			//System.out.println("SQL: " + comando);
			
			st.executeUpdate(comando);
			resultado = true;
		}catch(SQLException e){
			System.out.println("Erro ao inserir o registro: " + e.getMessage());
		}finally{
			desconectar();
		}
		return resultado;
	}

	public ArrayList<Cliente> buscarTodos(){
		ArrayList<Cliente> resultados = new ArrayList<Cliente>();
		try{
			conectar();
			ResultSet rs = st.executeQuery("select * from tb_clientes order by email;");
			while(rs.next()){
				Cliente c = new Cliente();
				c.setCodigo(rs.getInt("codigo"));
				c.setNome(rs.getString("nome"));
				c.setEmail(rs.getString("email"));
				c.setDataNascimento(rs.getString("dataNascimento"));
				c.setTelefone(rs.getLong("telefone"));
				
				resultados.add(c);
			}
		}catch(SQLException e){
			System.out.println("Erro: " + e.getMessage());
		}finally{
			desconectar();
		}
		return resultados;
	}	

	public int excluir(int cod){
		int qtde = 0;
		try{
			conectar();
			String comando = "delete from tb_clientes where codigo = " + cod + ";";
			st.executeUpdate(comando);
			qtde = st.getUpdateCount();//mostra quantos registros foram afetados no BD
		}catch(SQLException e){
			System.out.println("Erro: " + e.getMessage());
		}finally{
			desconectar();
		}		
		return qtde;
	}


	public Cliente consultar(int cod){
		Cliente c = null;
		try{
			conectar();
			String comando = "select * from tb_clientes where codigo = " + cod + ";";
			ResultSet rs = st.executeQuery(comando);
			if(rs.next()){
				c = new Cliente();
				c.setCodigo(rs.getInt("codigo"));
				c.setNome(rs.getString("nome"));
				c.setEmail(rs.getString("email"));
				c.setDataNascimento(rs.getString("dataNascimento"));
				c.setTelefone(rs.getLong("telefone"));
			}
		}catch(SQLException e){
			System.out.println("Erro: " + e.getMessage());
		}finally{
			desconectar();
		}
		return c;
	}

	public int alterar(Cliente c){
		int qtde = 0;
		try {
			conectar();
			String comando = "UPDATE  tb_clientes SET nome = '" + c.getNome()
					+ "', email = '" + c.getEmail() + "', dataNascimento = '" + c.getDataNascimento()
					+ "', telefone = " + c.getTelefone() + " WHERE codigo = " + c.getCodigo() + ";";
			st.executeUpdate(comando);
			qtde = st.getUpdateCount();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar:" + e.getMessage());
		} finally {
			desconectar();
		}
		return qtde;
	}
}






