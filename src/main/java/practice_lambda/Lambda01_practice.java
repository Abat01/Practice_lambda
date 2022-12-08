package practice_lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Lambda01_practice {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(Arrays.asList(-5, -8, -2, -12, 0, 1, 12, 5, 5, 6, 9, 15, 8));

        hepsiniYazdir(list);
        System.out.println();
        negatifYaz(list);
        System.out.println();
        ciftYaz(list);
        System.out.println();
        pozVeCift(list);
        System.out.println();
        pozVeyaCiftYaz(list);
        System.out.println();
        kareYazdir(list);
        System.out.println();
        ciftKupYazdir(list);
        System.out.println();
        kareTekrarsiz(list);
        System.out.println();
        elSirala(list);
        System.out.println();
        elTersSirala(list);
        System.out.println();
        System.out.println(ANSI_WHITE+"pozKup5List(list) = " + pozKup5List(list));
        pozKup5List2(list);
        System.out.println(ANSI_PURPLE+"pozKareSon5DegilList(list) = " + pozKareSon5DegilList(list));
        System.out.println(ANSI_YELLOW +"elToplam(list) = " + elToplam(list));
        System.out.println(ANSI_BLUE+"elToplamReference(list) = " + elToplamReference(list));
        System.out.println(ANSI_BLUE+"elToplamReferenceInt(list) = " + elToplamReferenceInt(list));
        pozElToplam(list);

    }


    // S1:listi aralarinda bosluk birakarak yazdiriniz

    public static void hepsiniYazdir(List<Integer>l){

        l.stream().forEach(t->System.out.print(t+" "));
    }
    //S2: sadece negatif olanlari yazdir
    public static void negatifYaz(List<Integer>l){

        l.stream().filter(t->t<0).forEach(t-> System.out.print(t+" "));
    }
    //S3: cift olanlardan yeni bir liste olustur
    public static void ciftYaz(List<Integer>l){

        l.stream().filter(t->t%2==0).forEach(t-> System.out.print(t+" "));


    }

    //S4:pozitif ve çift olanları yazdırın

    public static void pozVeCift(List<Integer>l){

        l.stream().filter(t->t%2==0 && t>0).forEach(t-> System.out.print(t+" "));
    }

    //S5:pozitif veya çift olanları yazdırın

    public static void pozVeyaCiftYaz(List<Integer>l){
        l.stream().filter(t->t%2==0 || t>0).forEach(t-> System.out.print(t+" "));
    }

    // S6: list in elemanlarin karelerini aynı satırda bır bosluklu yazdıralım

    //map():stream den gelen elemanları baska ttıplere donusturmek veya uzerlerınde
    //ıslem yapmak ıcın (update) kullanılır

    public static void kareYazdir(List<Integer>l){
        l.stream().map(t->t*t).forEach(t-> System.out.print(t+" "));
    }

    //S7: Listin cift elemanlarının kuplerini  aynı satırda bır bosluklu yazdıralım
    public static void ciftKupYazdir(List<Integer>l){

        l.stream().filter(t->t%2==0).map(t->t*t*t).forEach(t-> System.out.print(t+" "));
    }
    //S8 : list in elemanlarin karelerini tekrarsiz yazdıralım
    public static void kareTekrarsiz(List<Integer>l){

        l.stream().map(t->t*t).distinct().forEach(t-> System.out.print(t+" "));


        //distinct() => Bu method tekrarlı elemanları sadece bir kere akısa sokar.
        // Bu akışın farklı elemanlarından (Object.equals (Object) 'e göre) oluşan bir akış döndürür.
        // Sıralı akışlar için, farklı elemanın seçimi sabittir
        // (yinelenen öğeler için, karşılaşma sırasında ilk görünen öğe korunur.)
        // Sırasız akışlar için, herhangi bir kararlılık garantisi verilmez. Stream return eder.
    }

    //S9: listin elemanlarini kucukten buyuge siralayalim

    public static void elSirala(List<Integer>l){
        l.stream().sorted().forEach(t-> System.out.print(t+" "));
    }
    //S10: listin elemanlarini buyukten kucuge siralayalim
    public static void elTersSirala(List<Integer>l){
        l.stream().sorted(Comparator.reverseOrder()).forEach(t-> System.out.print(t+" "));
    }
    // S11: list pozitif elemanlari icn kuplerini bulup birler basamagi 5 olanlardan yeni bir list olusturalim
    public static List<Integer> pozKup5List(List<Integer>l){
        return l.stream().filter(t->t>0).map(t->t*t*t).filter(t->t%10==5).collect(Collectors.toList());
    }

    public static void pozKup5List2(List<Integer>l){
        List<Integer>list=l.stream().filter(t->t>0).map(t->t*t*t).filter(t->t%10==5).collect(Collectors.toList());
        System.out.println(list);
    }

    //S12: list pozitif elemanlari icn karelerini bulup birler basamagi 5 olmayanlardan yeni bir list olusturalim

    public static List<Integer> pozKareSon5DegilList(List<Integer>l){

        List<Integer> list=l.stream().filter(t->t>0).map(t->t*t).filter(t->t%10!=5).collect(Collectors.toList());
        return list;
    }

    // S13 :list elemanlarini toplamini bulalim
    public static int elToplam(List<Integer>l){
        int i=l.stream().reduce(0,(x,y)->x+y);
        return i;
    }


    public static int elToplamReference(List<Integer>l){
        int i=l.stream().reduce(0,Math::addExact);
        return i;
    }

    public static int elToplamReferenceInt(List<Integer>l){
        int i=l.stream().reduce(0,Integer::sum);
        return i;
    }

    //S14 : Listin pozitif elemanları toplamını bulalım

    public static void pozElToplam(List<Integer>l){

        System.out.println(ANSI_RED+l.stream().filter(t -> t > 0).reduce(0, Integer::sum));
    }


}

