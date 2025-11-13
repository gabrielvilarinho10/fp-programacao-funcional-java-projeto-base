package exercicios;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Uma lista de exemplos de algoritmos
 * "funcionais" mal escritos que
 * acabam usando recursos de programação funcional
 * de forma inapropriadada ou fazem uso de programação
 * estruturada no meio de código funcional, quando
 * há uma solução mais sucinta e fácil de entender.
 *
 * @author Manoel Campos
 */
public class CodigoFuncionalRuim {
    private final List<List<String>> listaCidadesPorLetraInicial = List.of(
            List.of("Aracajú", "Abreulândia"),
            List.of("Brejinho de Nazaré", "Bom Jesus do Tocantins"),
            List.of("Brasília", "Belém", "Belo Horizonte")
    );

    /**
     * Uma lista onde cada elemento é outra lista contendo
     * a distância entre os pontos de um polígono.
     * O total de elementos em cada lista interna indica o tipo de polígono,
     * como triângulo, qudrilátero, pentágono, etc.
     */
    private final List<List<Integer>> distanciasPoligonos = List.of(
            List.of(10, 10, 10),
            List.of(25, 25, 25),
            List.of(20, 10, 20, 10),
            List.of(30, 30, 30, 30, 30),
            List.of(10, 10, 15, 10, 15)
    );

    public CodigoFuncionalRuim() {
        codigoRuim1();
        correcao1();

        System.out.println();
        codigoRuim2();
        codigoRuim3();
        correcao2and3();

        System.out.println();
        codigoRuim4();
        correcao4();

        System.out.println();
        codigoRuim5();
        correcao5();

        System.out.println();
        codigoRuim6_1();
        codigoRuim6_2();
        correcao6();

        System.out.println();
        codigoRuim7();
        correcao7();

        System.out.println();
        codigoRuim8();
    }

    public static void main(String[] args) {
        new CodigoFuncionalRuim();
    }

    private void codigoRuim1() {
        var cidadesMap = new HashMap<Character, List<String>>();
        for (List<String> listaCidades : listaCidadesPorLetraInicial) {
            cidadesMap.put(listaCidades.getFirst().charAt(0), listaCidades);
        }

        var totalCidadesMap =
            cidadesMap
                .entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, p -> p.getValue().size()));
        System.out.println("Total de cidades por letra: " + totalCidadesMap);
    }

    private void correcao1() {
        try {
            var erro =
                listaCidadesPorLetraInicial
                    .stream()
                    .collect(
                        toMap(
                            lista -> lista.getFirst().charAt(0),
                            List::size
                        )
                    );
            System.out.println("Correção 1 (erro esperado se duplicar letra): " + erro);
        } catch (Exception e) {
            System.out.println("Correção 1: erro detectado, aplicando merge...");
        }

        var correto =
            listaCidadesPorLetraInicial
                .stream()
                .collect(
                    toMap(
                        lista -> lista.getFirst().charAt(0),
                        List::size,
                        Integer::sum
                    )
                );

        System.out.println("Correção 1 (map correto): " + correto);
    }

    private void codigoRuim2() {
        var totalCidadesList =
            IntStream
                .range(0, listaCidadesPorLetraInicial.size())
                .mapToObj(i -> listaCidadesPorLetraInicial.get(i).size())
                .toList();

        System.out.println("Total de cidades em cada grupo: " + totalCidadesList);
    }

    private void codigoRuim3() {
        var totalCidadesList = new ArrayList<Integer>();
        listaCidadesPorLetraInicial
                .forEach(cidades -> totalCidadesList.add(cidades.size()));

        System.out.println("Total de cidades em cada grupo: " + totalCidadesList);
    }

    private void correcao2and3() {
        var lista =
            listaCidadesPorLetraInicial
                .stream()
                .map(List::size)
                .toList();

        System.out.println("Correção 2 e 3: " + lista);
    }

    private void codigoRuim4() {
        var nomePoligonos =
            distanciasPoligonos
              .stream()
              .map(distancias -> {
                 switch (distancias.size()) {
                   case 3: return "Triângulo";
                   case 4: return "Quadrilátero";
                   case 5: return "Pentágono";
                   case 6: return "Hexágono";
                   default: return "Polígono de %d lados".formatted(distancias.size());
                 }
              })
              .toList();

        System.out.println("Tipos de polígonos: " + nomePoligonos);
    }

    private void correcao4() {
        var lista =
            distanciasPoligonos
                .stream()
                .map(this::tipoPoligono)
                .toList();

        System.out.println("Correção 4: " + lista);
    }

    private String tipoPoligono(List<Integer> distancias) {
        return switch (distancias.size()) {
            case 3 -> "Triângulo";
            case 4 -> "Quadrilátero";
            case 5 -> "Pentágono";
            case 6 -> "Hexágono";
            default -> "Polígono de %d lados".formatted(distancias.size());
        };
    }

    private void codigoRuim5() {
        var list =
              Stream.of(distanciasPoligonos)
                    .filter(distancias -> distancias.size() >= 4)
                    .toList();
        System.out.println("Polígonos com mais de 3 lados: " + list);
    }

    private void correcao5() {
        var lista =
            distanciasPoligonos
                .stream()
                .filter(l -> l.size() >= 4)
                .toList();

        System.out.println("Correção 5: " + lista);
    }

    private void codigoRuim6_1() {
        var perimetrosList =
                distanciasPoligonos.stream().flatMap(distancias -> {
                    var perimetro = 0;
                    for (Integer distancia : distancias) {
                        perimetro += distancia;
                    }
                    return Stream.of(perimetro);
                })
                                   .toList();

        System.out.println("Perímetros: " + perimetrosList);
    }

    private void codigoRuim6_2() {
        var perimetrosList =
                distanciasPoligonos
                    .stream()
                    .flatMap(distancias -> {
                        var perimetro = distancias.stream().mapToInt(d -> d).sum();
                        return Stream.of(perimetro);
                    }).toList();

        System.out.println("Perímetros: " + perimetrosList);
    }

    private void correcao6() {
        var lista =
            distanciasPoligonos
                .stream()
                .map(l -> l.stream().reduce(0, Integer::sum))
                .toList();

        System.out.println("Correção 6: " + lista);
    }

    private void codigoRuim7() {
        final Map<String, Integer> cidadesPorEstado = Map.of(
                "BA", 417,
                "CE", 184,
                "DF", 1,
                "ES", 78,
                "GO", 246,
                "MG", 853,
                "PA", 144,
                "RS", 497,
                "SP", 645,
                "TO", 139
        );

        var map = cidadesPorEstado
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 300)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("Estados com mais de 300 cidades:");
        map.forEach(
          (estado, cidades) -> System.out.printf("%s: %d cidades%n", estado, cidades)
        );
    }

    private void correcao7() {
        var mapa =
            cidadesPorEstado()
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 300)
                .collect(
                    Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum,
                        TreeMap::new
                    )
                );

        System.out.println("Correção 7:");
        mapa.forEach((k, v) -> System.out.printf("%s: %d cidades%n", k, v));
    }

    private Map<String, Integer> cidadesPorEstado() {
        return Map.of(
                "BA", 417,
                "CE", 184,
                "DF", 1,
                "ES", 78,
                "GO", 246,
                "MG", 853,
                "PA", 144,
                "RS", 497,
                "SP", 645,
                "TO", 139
        );
    }

    private void codigoRuim8() {
        var lista = listaCidadesPorLetraInicial
                        .stream()
                        .flatMap(List::stream)
                        .collect(toList());

        System.out.println("Lista de todas as cidades: " + lista);
    }
}
