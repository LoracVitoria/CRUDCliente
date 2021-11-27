import java.util.Scanner;

public class Sistema {

	public static void main(String[] args) {
		Sistema sis = new Sistema();
		sis.menuPrincipal();
	}
	
	public void menuPrincipal(){
		int opcao = -1;
		Scanner scanner = new Scanner(System.in);
		while(opcao != 0){
			System.out.println("\n--------------------------------------------------------------");
			System.out.println("MENU PRINCIPAL");
			System.out.println("[1] Gerenciar Clientes");
			System.out.println("[0] Sair");
			System.out.println("--------------------------------------------------------------\n");
			try{
				opcao = Integer.parseInt(scanner.nextLine());		
			} catch (Exception e) {
				System.out.println("Erro! \n Informe um número inteiro");
			}
			
			if(opcao == 1){
				GerenciadorCliente gCliente = new GerenciadorCliente();
				gCliente.menu();
			}else if(opcao == 0){
				System.out.println("Até logo!");
			}else{
				System.out.println("Opção inválida!");
			}
		}
		
	}	
}
