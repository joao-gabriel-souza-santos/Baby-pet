package com.example.projetobabypet.controller;

import com.example.projetobabypet.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuario {

    private int proxId;
    private final List<Usuario> usuarios; //crio uma lista final pra ser imutavel
    private static  ControllerUsuario instancia = null; //crio um atributo que vai instanciar meu controler

    private ControllerUsuario(){ //quando ele criar um controller ele vai criar uma lista e um id pro usuario
        usuarios = new ArrayList<>();
        proxId = 1;
    }

    public int getProxId(){
        return proxId;
    }

    public static ControllerUsuario getInstancia(){
        if(instancia == null) //verifica se o controller já foi instanciado
            instancia = new ControllerUsuario(); //se não for instanciado, ele cria uma instancia gerando uma lista
        return instancia;
    }

    public List<Usuario> buscarTodos(){
        return new ArrayList<>(usuarios);
    } //retorna todos os usuarios da lista


    public boolean alterarSenha(Usuario usuario,String senha){ //recebe um usuario e a senha pra ser alterada
        for (int i = 0; i< usuarios.size(); ++i){ //crio um for pra percorrer a lista
            if(usuario.getId()==usuarios.get(i).getId()){ //verifico cada usuario pelo seu indice que vai ser igual ao seu codigo
                usuario.setSenha(senha);  //quando ele encontrar um usuario com o mesmo codigo do usuario que quer alterar a senha
                                            // ele vai settar a nova senha do usuario
                usuarios.set(i, usuario); //e vai settar na lista
                return true; //retorna que a operação foi um sucesso
            }
        }
        return false; //retorna que a operação falhou
    }

    public void cadastrar(Usuario usuario){ //recebo o usuario a ser cadastrado
        usuario.setId(proxId); //setto o id do usuario
        boolean resultado = usuarios.add(usuario); //realizo o cadastro e verifico se teve algum erro
        if(resultado){ //se o resultado for um sucesso
            proxId+=1; //ele cria o prox id
        }
    }
    
    public boolean login(String email, String senha){ //recebe o email e a senha a ser verificado
        for (Usuario usuario: usuarios) { //pra cada usuario na lista
            if((usuario.getEmail().equals(email)) && (usuario.getSenha().equals(senha))) { // pra cada usuario da lista
                                                                                            //ele vai verificar qual usuario tem a senha igual a senha digitada
                return true; //retorna verdadeiro se ele encontrar algum usuario com o email e senha correspondente
            }
        }
        return false; //se não encontrar então retorna falso
    }


    public Usuario buscarporId(int id){ //pesquisa o id do usuario
        for(Usuario usuario: usuarios){ //pra cada usuario na lista
            if(usuario.getId()==id){ //ele verifica se o id recebido é igual ao id do usuario da vez
                return usuario; //se for então ele me retorna o usuario com o id igual ao id recebido
            }
        }
        return null;
    }

    public Usuario buscarPorCpf(String cpf){ //recebe o cpf do usuario
        for(Usuario usuario:usuarios){ //pra cada usuario na lista
            if(usuario.getCpf().toString().equals(cpf)){ // ele verifica se o cpf recebido é igual a de algum usuario da lista
                return usuario; //se for igual então ele retorna o usuario com o id igual ao recebido
            }
        }
        return null; //se não encontrar então retorna nullo
    }
    public Usuario buscarPorEmail(String email){ //recebe o email do usuario
        for(Usuario usuario:usuarios){ //pra cada usuario na lista
            if(usuario.getEmail().toString().equals(email)){ //verifica se tem algum usuario com o email igual ao email recebido
                return usuario;// se tiver então retorna verdadeiro
            }
        }
        return null;
    }

}
