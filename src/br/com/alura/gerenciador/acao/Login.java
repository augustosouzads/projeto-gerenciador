package br.com.alura.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Usuario;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		System.out.println("Logando " + login);
		
		Banco banco = new Banco();
		Usuario usuario = banco.existeUsuario(login,senha);
		
		if(usuario != null) { //se tem usuario  
			System.out.println("Usuario existe");
			HttpSession sessao = request.getSession();//peguei a sessao e o cookie da requisição
			sessao.setAttribute("usuarioLogado", usuario);//coloquei o usuario no objeto da sessao
			return "redirect:entrada?acao=ListaEmpresas";
		}else {
			System.out.println("Usuario não existe");
			return "redirect:entrada?acao=LoginForm";
		}
		
	}

}
