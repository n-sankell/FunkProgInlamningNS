package Inlämningsuppgift2


/*
Tomtarna på Nordpolen har en strikt chefs-hierarki
Högsta chefen för allt är "Tomten"
Under "Tomten" jobbar "Glader" och "Butter"
Under "Glader" jobbar "Tröger", "Trötter"och "Blyger"
Under "Butter" jobbar "Rådjuret", "Nyckelpigan", "Haren" och "Räven"
Under "Trötter" jobbar "Skumtomten"
Under "Skumtomten" jobbar "Dammråttan"
Under "Räven" jobbar "Gråsuggan" och "Myran"
Under "Myran" jobbar "Bladlusen"
Er uppgift är att illustrera tomtens arbetshierarki i en lämplig datastruktur.
(Det finns flera tänkbara datastrukturer här)
Skriv sedan en funktion där man anger ett namn på tomten eller någon av hens underhuggare som
inparameter.
Funktionen listar sedan namnen på alla underlydandesom en given person har
Expempel: Du anger "Trötter" och får som svar ["Skumtomten", "Dammråttan"]"
För att bli godkänd på uppgiften måste du använda rekursion.
 */

class Tomteland {

    class SantaNode<String>(val name: String) {
        private val underlings: MutableList<SantaNode<String>> = mutableListOf()
        fun addUnderling(underling: SantaNode<String>) = underlings.add(underling)
        fun getList(): List<SantaNode<String>> {
            return underlings
        }
    }

    private fun populateTree(): MutableList<SantaNode<String>> {
        val tomten = SantaNode("Tomten")
        val glader = SantaNode("Glader")
        val butter = SantaNode("Butter")
        val troger = SantaNode("Tröger")
        val trotter = SantaNode("Trötter")
        val blyger = SantaNode("Blyger")
        val radjuret = SantaNode("Rådjuret")
        val nyckelpigan = SantaNode("Nyckelpigan")
        val haren = SantaNode("Haren")
        val raven = SantaNode("Räven")
        val skumtomten = SantaNode("Skumtomten")
        val dammrattan = SantaNode("Dammråttan")
        val grasuggan = SantaNode("Gråsuggan")
        val myran = SantaNode("Myran")
        val bladlusen = SantaNode("Bladlusen")
        tomten.addUnderling(glader).and(tomten.addUnderling(butter))
        glader.addUnderling(troger).and(glader.addUnderling(trotter).and(glader.addUnderling(blyger)))
        butter.addUnderling(radjuret).and(butter.addUnderling(nyckelpigan).and(butter.addUnderling(haren)).and(butter.addUnderling(raven)))
        trotter.addUnderling(skumtomten)
        skumtomten.addUnderling(dammrattan)
        raven.addUnderling(grasuggan).and(raven.addUnderling(myran))
        myran.addUnderling(bladlusen)
        return mutableListOf(tomten)
    }

    // current namn är den tomte vars underlydande ni vill ta fram
    //res är listan som håller alla underlydande
    fun getUnderlings(currentName: String, res: MutableList<String>): List<String> {
        val tomten = populateTree()

        fun addUnderlingsToList(nextLevel: List<SantaNode<String>>) {
            for (i in nextLevel) {
                if (!res.contains(i.name)) {
                    res.add(i.name)
                }
                if (i.getList().isNotEmpty()) {
                    i.getList().forEach { res.add(it.name) }
                    addUnderlingsToList(i.getList())
                }
            }
        }

        fun searchTree(tomten: List<SantaNode<String>>) {
            for (i in tomten) {
                if (i.name == currentName) {
                    if (i.getList().isNotEmpty()) {
                        addUnderlingsToList(i.getList())
                    }
                } else {
                    searchTree(i.getList())
                }
            }
        }
        searchTree(tomten)

        return res
    }

    fun main() {
        //Exempel på anrop till den rekursiva funktionen getUnderlings,
        // här är tanken att hitta Gladers underlydande
        //listan fylls på successivt när vi rekurserar
        var list: MutableList<String> = mutableListOf()
        println(getUnderlings("Glader", list))

    }
}