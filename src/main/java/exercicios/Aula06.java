package exercicios;

import exercicios.base.Aula;

import java.util.List;
import java.util.function.Predicate;
import java.util.Comparator;

/**
 * Esta é uma classe para você poder implementar as atividades propostas no README.
 * Você <b>NÃO</b> deve alterar:
 * <ul>
 *     <li>a estrutura deste arquivo;</li>
 *     <li>o nome da classe, dos métodos ou dos atributos;</li>
 *     <li>parâmetros e tipo de retorno dos métodos.</li>
 * </ul>
 *
 * <b>Mas você PRECISA alterar valores dos atributos existentes</b>.
 *
 * <p>Você pode alterar o código interno dos métodos, criar métodos auxiliares que podem ser chamados
 * pelos existentes, mas não deve alterar a estrutura dos métodos disponíveis.</p>
 *
 * @author Manoel Campos da Silva Filho
 */
public class Aula06 extends Aula {
    /**
     * {@link Predicate<Estudante>} que seleciona somente as mulheres
     * matriculadas em algum curso e com nota maior ou igual a 6.
     * Este deve ser um predicado composto usando {@link Predicate#and(Predicate)}.
     * Você deve trocar o valor armazenado ao atributo para ele seguir a regra definida acima.
     */
    private final Predicate<Estudante> mulheresAprovadas =
            e -> e.getSexo() == 'F'
            && e.getCurso() != null
            && e.getNota() >= 6;

    /**
     * Você pode chamar os métodos existentes e outros que você criar aqui,
     * incluir prints e fazer o que desejar neste método para conferir os valores retornados pelo seu método.
     * Para verificar se sua implementação está correta, clique com o botão direito no nome do projeto na aba esquerda
     * do IntelliJ e selecione a opção "Run All Tests".
     */
    public Aula06() {
        // Exemplos de chamada (opcionais):
        // System.out.println(getEstudantesMulheresAprovadas());
        // System.out.println(getEstudantesMulheresAprovadasOrdenadasPorCursoAndNota());
    }

    /**
     * Veja o método construtor {@link #Aula06()}.
     */
    public static void main(String[] args) {
        new Aula06();
    }

    public List<Estudante> getEstudantesMulheresAprovadas() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .toList(); // lista NÃO-modificável
    }

    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoAndNota() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(
                        Comparator.comparing((Estudante e) -> e.getCurso().getNome())
                                .thenComparing(Estudante::getNota)
                )
                .toList(); // NÃO-modificável
    }

    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoDecrescenteAndNotaCrescente() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(
                        Comparator.comparing((Estudante e) -> e.getCurso().getNome()).reversed()
                                .thenComparing(Estudante::getNota)
                )
                .toList(); // NÃO-modificável
    }

    public List<Estudante> getEstudantesMulheresAprovadasNaoOrdenadasModificavel() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .toList()         // cria lista não-modificável
                .stream()
                .toList();        // converte para lista modificável (Java 16+)
    }

    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasTotalmenteDecrescente() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(
                        Comparator.comparing((Estudante e) -> e.getCurso().getNome()).reversed()
                                .thenComparing(Estudante::getNota, Comparator.reverseOrder())
                )
                .toList(); // NÃO-modificável
    }

    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoCrescenteAndNotaDecrescente() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(
                        Comparator.comparing((Estudante e) -> e.getCurso().getNome())
                                .thenComparing(Estudante::getNota, Comparator.reverseOrder())
                )
                .toList(); // NÃO-modificável
    }
}