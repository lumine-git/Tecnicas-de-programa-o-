package br.edu.fatecpg.Tecprog2;
import br.edu.fatecpg.Tecprog2.model.ReceitasPromocao;
import br.edu.fatecpg.Tecprog2.repository.ReceitasPromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tecprog2Application implements CommandLineRunner {
	/*@Autowired
	private AlunoRepository rep;*/
	@Autowired
	private ReceitasPromocaoRepository pep;
	public static void main(String[] args) {
		SpringApplication.run(Tecprog2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		/*
		Aluno a1 = new Aluno("Edd","112.345.678-10");
		Aluno a2 = new Aluno("Ordep","980.192.670-11");
		Aluno a3 = new Aluno("Maligna","113.323.665-12");
		rep.save(a1);
		rep.save(a2);
		rep.save(a3);*/
		/*
		Aluno a4 = new Aluno("Ronaldo","222.555.676-67","12345678");
		Aluno a5 = new Aluno("Pedro","111.222.333-45","987654321");
		Aluno a6 = new Aluno("Saltão","444.555.666-87","09876541");
		rep.save(a4);
		rep.save(a5);
		rep.save(a6);

		List<Aluno> Gurizada = rep.findAll();
		for(int c=0; c<Gurizada.size(); c++){
			System.out.println("Nome: " + Gurizada.get(c).getNome() + " || " + "Matricula: " + Gurizada.get(c).getMatricula());
		}*/

		/*System.out.println(rep.buscarPorNome("Edd"));
		System.out.println(rep.findById(1L));
		rep.deleteById(2L);
		System.out.println(rep.findAll());*/

		ReceitasPromocao rp1 = new ReceitasPromocao("Mexerica",true,"Frutas");
		ReceitasPromocao rp2 = new ReceitasPromocao("Banana",false,"Frutas");
		ReceitasPromocao rp3 = new ReceitasPromocao("Maçã",true,"Frutas");
		ReceitasPromocao rp4 = new ReceitasPromocao("Uva",false,"Frutas");
		ReceitasPromocao rp5 = new ReceitasPromocao("Melão",true,"Frutas");
		ReceitasPromocao rp6 = new ReceitasPromocao("Limão",true,"Frutas");

		pep.save(rp1);
		pep.save(rp2);
		pep.save(rp3);
		pep.save(rp4);
		pep.save(rp5);
		pep.save(rp6);

		pep.BuscarPromocao(true).forEach(System.out::println);

	}

}

