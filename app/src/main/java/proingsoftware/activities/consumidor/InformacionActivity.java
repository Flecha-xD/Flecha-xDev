package proingsoftware.activities.consumidor;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.R;

import proingsoftware.Adapters.NormativaAdapter;
import proingsoftware.model.Normativa;
import java.util.LinkedList;
import java.util.List;

public class InformacionActivity extends AppCompatActivity { //en teoria ya esta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewInfo);
        List<Normativa> normativaList = new LinkedList<>();
        normativaList.add (new Normativa ("Ley N° 453","La presente " +
                "Ley tiene por objeto regular los derechos y garantías de las usuarias y" +
                " los usuarios, las consumidoras y los consumidores. \n " +
                "En aplicación del Artículo 297, Parágrafo II de la Constitución Política\n" +
                "del Estado y el Artículo 72 de la Ley Nº 031 de 19 de julio de 2010, Marco de Autonomías y Descentralización “Andrés Ibáñez”, se " +
                "asigna al nivel central del Estado, la competencia exclusiva de desarrollar los derechos, garantías y políticas de las usuarias y los " +
                "usuarios, las consumidoras y los consumidores, en el ámbito nacional y sectorial, sin perjuicio de la competencia exclusiva del nivel " +
                "Municipal. \n" + "Están sujetos a las disposiciones de la presente Ley, los proveedores de productos o servicios, así como las usuarias " +
                "y los usuarios, las consumidoras y los consumidores, en sus relaciones de consumo."));
        normativaList.add (new Normativa ("Reglamento de Regulación de Supermercados",
                " El presente decreto supremo tiene por objeto la regulación del" +
                        " establecimiento y funcionamiento de las bolsas de productos, los mercados " +
                        "por ellas organizados, los sujetos que participen en los mismos, los actos y " +
                        "contratos que se celebren, así como los productos negociables en dichos mercados, " +
                        "de acuerdo con el Capítulo II del Título IV de la Ley del Mercado de Valores. \n"+
                "Las normas de regulación contenidas en el presente decreto supremo tienen como finalidad, hacer cumplir las condiciones de transparencia, " +
                        "liquidez, seguridad, libre competencia e igualdad de trato para todos los participantes de los mercados de productos, a través del" +
                        " establecimiento de los mecanismos que contribuyan a la organización y desarrollo de dichos mercados mediante las Bolsas. \n" +
                "Para efectos del presente decreto supremo se establecen las siguientes definiciones, las mismas que tienen carácter enunciativo y no limitativo:\n" +
                        "\n" +
                        "Agencias de Bolsa o Agencias: Se refiere a las Agencias de Bolsa inscritas en el Registro público a cargo de la Superintendencia de" +
                        " Pensiones, Valores y Seguros y autorizadas para realizar las actividades previstas en el presente Decreto Supremo y demás normas aplicables.\n" +
                        "Autorización: Es el acto en virtud del cual la Superintendencia de Pensiones, Valores y Seguros, mediante resolución, autoriza el funcionamiento u " +
                        "operaciones de las personas naturales y jurídicas participantes en el mercado de productos, según lo establecido en el presente decreto supremo y " +
                        "demás disposiciones aplicables.\n" +
                        "Bolsas de Productos o Bolsa: Se refiere a las Bolsas de Productos autorizadas e inscritas ante la Superintendencia de Pensiones, Valores y Seguros.\n" +
                        "Inscripción: Es el acto en virtud del cual la Superintendencia de Pensiones, Valores y Seguros mediante Resolución Administrativa inscribe las personas" +
                        " naturales y jurídicas, productos, valores, actividades u otros por ella autorizados en el Registro de la Superintendencia.\n" +
                        "La Ley: Se refiere a la Ley Nº 1834, de 31 de marzo de 1998, del Mercado de Valores y sus reformas.\n" +
                        "Mercado de Productos: Define las negociaciones realizadas por medio de las agencias de bolsa en los recintos de Bolsa, con productos autorizados.\n" +
                        "Operadores de Bolsa de Productos u Operadores: Se refiere a los operadores de las Bolsas de Productos autorizados e inscritos ante la Superintendencia de" +
                        " Pensiones, Valores y Seguros.\n" +
                        "Productos: Toda alusión a los términos “Producto” o “Productos” comprenderá aquellos de origen agropecuario, pesquero, forestal y minero dentro de los alcances" +
                        " del presente decreto supremo.\n" +
                        "Superintendencia o SPVS: Se refiere a la Superintendencia de Pensiones, Valores y Seguros creada mediante la Ley Nº 1864, de 15 de junio de 1998, de Propiedad " +
                        "y Crédito Popular."));
        normativaList.add (new Normativa ("Reglamento de Publicidad Engañosa y Abuso",
                "El presente Reglamento tiene por objeto reglamentar el proceso de verificación y" +
                        " restauración en protección a los Derechos a la Información y a la libre elección, " +
                        "previniendo el incumplimiento del servicio ofertado y la utilización de la Publicidad " +
                        "Engañosa o Abusiva de productos y servicios, así como la aplicación de sanciones administrativas. \n " +
                "Están sujetos al cumplimiento del\n" +
                        "presente Reglamento, todas las personas naturales y/o jurídicas del sector no\n" +
                        "regulado en cualquier parte del territorio nacional que ofrezcan servicios o\n" +
                        "productos a usuarias, usuarios o consumidoras, consumidores \n" + "Son las siguientes disposiciones normativas:\n" +
                        "a) Constitución Política del Estado.\n" +
                        "b) Ley Nº 453, de 4 de diciembre del 2013, “Ley General de los Derechos de las\n" +
                        "Usuarias y los Usuarios y de las Consumidoras y los Consumidores”.\n" +
                        "c) Decreto Supremo N° 2130, de 24 de septiembre del 2014, Reglamento de la\n" +
                        "Ley N° 453.\n" +
                        "d) Resolución Ministerial Nº055/2015, de 24 de marzo de 2015, “Reglamento de\n" +
                        "Procedimiento de Reclamaciones, de Revisión y Régimen Sancionatorio del\n" +
                        "Centro de Atención al Usuario y al Consumidor”.\n" +
                        "e) Resolución Ministerial Nº081/2015, de 29 de abril de 2015, “Reglamento de\n" +
                        "Verificación de Servicios y Productos en aplicación del Derecho a la\n" +
                        "información y Prevención de la Publicidad e Información Engañosa o abusiva”."));
        NormativaAdapter adapter = new NormativaAdapter(this, normativaList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
