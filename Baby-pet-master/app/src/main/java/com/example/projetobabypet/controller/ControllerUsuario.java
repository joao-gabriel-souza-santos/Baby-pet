package com.example.projetobabypet.controller;

import android.content.Context;
import android.widget.Toast;

import com.example.projetobabypet.dao.UsuarioRepositorio;
import com.example.projetobabypet.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControllerUsuario {

    private Context contexto;
    public static List<Usuario> usuarios; //crio uma lista final pra ser imutavel
    private static  ControllerUsuario instancia = null; //crio um atributo que vai instanciar meu controler
    public UsuarioRepositorio db;

    private ControllerUsuario(Context contexto){ //quando ele criar um controller ele vai criar uma lista e um id pro usuario
        usuarios = new ArrayList<>();
        this.contexto = contexto;
    }



    public static ControllerUsuario getInstancia(Context contexto){
        if(instancia == null) //verifica se o controller já foi instanciado
            instancia = new ControllerUsuario(contexto); //se não for instanciado, ele cria uma instancia gerando uma lista
        return instancia;
    }

    public List<Usuario> buscarTodos() throws Exception {
            db = new UsuarioRepositorio(contexto);
            usuarios = db.listar();
            return new ArrayList<>(usuarios);

    } //retorna todos os usuarios da lista


    public boolean alterarSenha(Usuario usuario,String senha){ //recebe um usuario e a senha pra ser alterada
        try{
            for (int i = 0; i< usuarios.size(); ++i){ //crio um for pra percorrer a lista
                if(usuario.getId()==usuarios.get(i).getId()){ //verifico cada usuario pelo seu indice que vai ser igual ao seu codigo
                    usuario.setSenha(senha);  //quando ele encontrar um usuario com o mesmo codigo do usuario que quer alterar a senha
                    // ele vai settar a nova senha do usuario
                    db.atualizarUsuario(usuario);
                    atualizarLista();
                    return true; //retorna que a operação foi um sucesso
                }
            }
            return false; //retorna que a operação falhou
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void cadastrar(Usuario usuario, Context context){ //recebo o usuario a ser cadastrado
            UsuarioRepositorio usuDAO = new UsuarioRepositorio(context);
            long id = usuDAO.inserirUsuario(usuario);
    }
    
    public boolean login(String email, String senha) throws Exception { //recebe o email e a senha a ser verificado
        atualizarLista();
        usuarios = buscarTodos();
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

    public Usuario buscarPorCpf(String cpf) throws Exception { //recebe o cpf do usuario
        atualizarLista();
        usuarios = buscarTodos();
        for(Usuario usuario:usuarios){ //pra cada usuario na lista
            if(usuario.getCpf().toString().equals(cpf)){ // ele verifica se o cpf recebido é igual a de algum usuario da lista
                return usuario; //se for igual então ele retorna o usuario com o id igual ao recebido
            }
        }
        return null; //se não encontrar então retorna nullo
    }
    public Usuario buscarPorEmail(String email) throws Exception{ //recebe o email do usuario
        atualizarLista();
        usuarios = buscarTodos();
        for(Usuario usuario:usuarios){ //pra cada usuario na lista
            if(usuario.getEmail().equals(email)){ //verifica se tem algum usuario com o email igual ao email recebido
                return usuario;// se tiver então retorna verdadeiro
            }
        }
        return null;
    }

    public void atualizarLista() throws Exception {
        usuarios.clear();
        usuarios.addAll(buscarTodos());
    }

    public void atualizarUsuario(Usuario usuario){
        db = new UsuarioRepositorio(contexto);
        db.atualizarUsuario(usuario);
    }
    public void atualizar(Usuario usuario, String emailAntigo){
        db = new UsuarioRepositorio(contexto);
        db.atualizar(usuario, emailAntigo);
    }
    public void atualizarqtdeAgua(String email, int valor){
        db = new UsuarioRepositorio(contexto);
        db.atualizaqtdeAgua(email, valor);
    }
    public void atualizarqtdeRacao(String email, int valor) {
        db = new UsuarioRepositorio(contexto);
        db.atualizaqtdeRacao(email, valor);
    }
    public void atualizarSomaRacao(String email, int valor){
        db = new UsuarioRepositorio(contexto);
        db.atualizaSomaRacao(email, valor);
    }
    public  void atualizarSomaAgua(String email, int valor) {
        db = new UsuarioRepositorio(contexto);
        db.atualizaSomaAgua(email, valor);
    }
    public void atualizaMaxAgua(String email, int valor){
        db = new UsuarioRepositorio(contexto);
        db.atualizamaxAgua(email, valor);
    }
    public void atualizaMaxRacao(String email, int valor) {
        db = new UsuarioRepositorio(contexto);
        db.atualizaMaxRacao(email, valor);

    }
    }
