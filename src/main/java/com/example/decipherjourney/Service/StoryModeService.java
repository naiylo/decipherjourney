package com.example.decipherjourney.Service;

import com.example.decipherjourney.Model.StoryMode;
import com.example.decipherjourney.Model.StoryOne;
import com.example.decipherjourney.Model.StoryTwo;
import com.example.decipherjourney.Model.StoryThree;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * StoryModeService handles the initialization and progression of stories 
 * within the application. It provides functionality to move between story parts
 * and manage the user's progress.
 * 
 * @author Oskar Schiedewitz
 */
@Service
public class StoryModeService {

    /**
     * Initializes the story mode with story parts.
     */
    public StoryMode initializeStoryParts() {

        StoryMode storyMode = new StoryMode();
        List<Object> partList = new ArrayList<>();

        StoryOne storyOne = new StoryOne();
        StoryTwo storyTwo = new StoryTwo();
        StoryThree storyThree = new StoryThree();

        // Initialize all of story one
        
        storyOne.setDialogs(List.of(List.of(
            "Unbekannter Roboter: Guten Morgen, Reisender! Kannst du mich hören? Mein Name ist Kip. ≧◉◡◉≦",
            "Kip: Falls du dich nicht mehr daran erinnern kannst, wir befinden uns auf einer Reise durch Zeit und Raum und ich bin dein mechanischer Begleiter.",
            "Kip: Mein Erbauer, dein Vater, hat uns eine wichtige Mission anvertraut.",
            "Kip: Wir sollen den Tresor hier neben mir an der Wand öffnen. Aber der Weg dorthin ist voller Rätsel und Geheimnisse.",
            "Kip: Die einzelnen Türen des Tresors lassen sich durch das Lösen von Chiffren öffnen.",
            "Kip: Was Chiffren sind? Okay ich probiere es dir zu erklären!",
            "Kip: Chiffren sind dafür da um Texte zu verschlüsseln beziehungsweise zu entschlüsseln.",
            "Kip: Stell dir vor du hast das Wort ROBOTER. Wenn wir jetzt jeden einzelnen Buchstaben um zwei Buchstaben im Alphabet nach hinten verschieben. Wird das R zu einem T und immer so weiter.",
            "Kip: Wir haben dann das Wort ROBOTER mit TQDQVDT ersetzt und es nicht mehr so leicht erkennbar welches Wort wohl dahinter steckt.",
            "Kip: Unsere erste Station ist das alte Rom. Dort liegt der Ursprung einer der faszinierendsten Verschlüsselungsmethoden: der Caesar-Chiffre.",
            "Kip: Bist du bereit, in die Welt der alten Römer einzutauchen und die Geheimnisse zu entschlüsseln, die Julius Caesar selbst genutzt hat?",
            "Kip: Die Caesar-Chiffre ist eine Verschlüsselungstechnik, bei der Buchstaben im Alphabet um eine bestimmte Anzahl von Stellen verschoben werden. Genau so wie ich es dir gerade kurz gezeigt habe!",
            "Kip: In Rom werden wir herausfinden, wie sie funktioniert, indem wir selbst Rätsel lösen.",
            "Kip: Lass uns hier rüber gehen und ich öffne das Portal. Halte dich fest, Reisender – es wird ein wenig holprig!",
            "Kip: Auf geht's nach Rom! Und vergiss nicht Reisender ich bin hier um dir zu helfen. Los geht’s!",
            ""
        )
        ));

        storyOne.setStoryParts(List.of(
            "Kapitel 1: Der Anfang einer Freundschaft"
        ));

        storyOne.setCheckpoint("Kapitel 1: Der Anfang einer Freundschaft");
        
        partList.add(storyOne);
        System.out.println("Added Part 1.");

        // Initialize all of story two

        storyTwo.setDialogs(List.of(List.of(
            "Kip: Willkommen im antiken Rom, Reisender! Ist es nicht atemberaubend?",
            "Kip: Diese Stadt ist das Herz einer der einflussreichsten Kulturen der Geschichte. Hier begann das Abenteuer der Caesar-Chiffre!",
            "Kip: Schaue dich doch erstmal kurz um, bevor wir weiterlaufen.",
            "...",
            "Fremder Verkäufer: Salve, du hast aber lustige Sachen an. Du kommst wohl aus einem fernen Land?",
            "Fremder Verkäufer: Dir kann ich wohl keine meiner 12 Ziegen verkaufen, aber hier hast du eine alte Scheibe die wir früher als Kinder benutzt haben um Texte zu verschlüsseln und jetzt geh weiter!",
            "...",
            "Kip: Das ist die Statue vom römischen Gott Zeus einem der 12 wichtigsten Götter!",
            "Kip: Zeus ist der Gott der Blitze und des Donners und nebenbei auch noch der oberste Gott in der römischen Mythologie.",
            "Kip: Hey lass uns doch irgendwo hingehen, wo es etwas ruhiger ist, oder?",
            "Kip: Dort kann ich dir mehr zur Caesar Chiffre erzählen",
            "Kip: Die Caesar-Chiffre war eine der ersten Verschlüsselungstechniken. Julius Caesar selbst nutzte sie, um seine Botschaften zu sichern.",
            "Kip: Lass mich dir zeigen, wie sie funktioniert!",
            "Kip: Eigentlich ist es ganz einfach. Jeder Buchstabe wird um eine feste Zahl verschoben, wie man es an der Schiebetafel sieht, die du geschenkt bekommen hast.",
            "Kip: Bei einem Schlüssel von drei also einer Verschiebung von 3. Rotiert das R zu einem U das O zu einem R und das M zu einem P. ",
            "Kip: Aus dem Wort R O M wird dann U R P und die eigentliche Stadt ist nicht mehr so leicht zu erkennen.",
            "Kip: Um eben solche wichtigen Aspekte in Nachrichten und Briefen zu verschlüsseln wurde die Caesar Chiffre genutzt.",
            "[Klicke weiter um das Tutorial zu beenden, oder starte den Story Strang neu um die Erklärung von Kip erneut zu hören.]",
            "Kip: Jetzt wo du weißt wie sie funktioniert kannst du es ja selber kurz ausprobieren. Hier schau auf diese Tafel und verschlüssele das Wort Kolosseum, indem du einen Verschiebung von 3 nutzt.",
            "Kip: Lass dir ruhig Zeit. ≧◉◡◉≦ ",
            "...",
            "Kip: Sehr gut gemacht jetzt wo du verstanden hast, wie man damit Wörter Verschlüsselt, solltest du Versuchen ein Wort zu Entschlüsseln.",
            "Kip: Während unserer Zeit hier hast du schon einen Hinweis auf die Schlüsselgröße bekommen, aber probier dich ruhig aus. ≧◉◡◉≦",
            "...",
            "Kip: Toll du hast die Caesar Chiffre damit vollständig verstanden. Wir haben den ersten Schritt auf unserer Reise geschafft. ≧◉◡◉≦",
            "Kip: Der nächste Halt ist die Vigenère Chiffre. Sie führt uns direkt in das Herz von London!",
            "Kip: Auf durch das Portal. Folge mir einfach!!",
            "..."
            )));

        storyTwo.setStoryParts(List.of(
            "Kapitel 2: Das antike Reich"
        ));

        storyTwo.setCheckpoint("Kapitel 2: Das antike Reich");
        partList.add(storyTwo);
        System.out.println("Added Part 2.");

        // Initialize all of story three

        storyThree.setDialogs(List.of(List.of(
            "Kip: Willkommen in London, einer Stadt voller Geschichte und Geheimnisse! Hier werden wir die Vigenère-Chiffre kennenlernen, eine der raffiniertesten Verschlüsselungstechniken.",
            "Kip: Schau dich doch erstmal kurz um, bevor wir weiterlaufen.",
            "Kip: Sprich vielleicht mit dieser Person da. Vielleicht kann sie dir etwas über die Chiffre erzählen?",
            "...",
            "Fremder: Guten Tag! Du siehst aus, als wärst du auf einer spannenden Reise. Kann ich dir vielleicht etwas anbieten? Vielleicht eine Zeitung, da sind lustige Rätsel drin?",
            "Fremder: Du interessierst dich für die Vigenère Chiffre, mmh dann schau doch mal in die Zeitung, ich gebe sie dir umsonst. Übrigens mein Name ist Sherlock.",
            "Sherlock: Leider muss ich jetzt weiter, ich stecke mitten in einem Fall. Viel Glück dir bei deinem Rätsel.",
            "...",
            "Kip: Mit der Zeitung lässt sich doch arbeiten.",
            "Kip: Das hier hinter mir ist der berühmte Big Ben! Ein Wahrzeichen Londons und ein Symbol für Präzision und Beständigkeit.",
            "Kip: Schau mal was da vorne liegt, diese Tafel sieht ja fast so aus wie die, aus dem alten Rom.",
            "Kip: Das hängt wohl damit zusammen, dass die Caesar Chiffre eine einfache Version der Vigenère Chiffre ist.  ≧◉◡◉≦",
            "Kip: Lass uns doch mal hier rüber gehen, dann erzähle ich dir was ich erfahren habe über die Vigenère Chiffre.",
            "Kip: Die Vigenère Chiffre funktioniert ähnlich zur Caesar Chiffre durch eine Verschiebung der Buchstaben, es wird jedoch nicht nur eine Zahl genutzt sondern ein ganzes Schlüsselwort.",
            "Kip: Die Zeichen des Schlüsselwortes stehen dabei für ihre jeweilige Zahl im Alphabet.",
            "Kip: Die zeichen des ursprünglichen Textes werden dann der Reihenfolge nach um die aufeinanderfolgenden Zahlen verschoben.",
            "Kip: Damit ist nicht jedes A der selbe Buchstabe aber bei einer Schlüssellänge von 4 wird jeder vierte Buchstabe um die selbe Zahl vom vierten Zeichen im Schlüssel verschoben.",
            "Kip: Hier ich habe dir eine kurze Erklärung auf das Tablet geladen.",
            "[Klicke weiter um das Tutorial zu beenden, oder starte den Story Strang neu um die Erklärung von Kip erneut zu hören.]",
            "Kip: Jetzt wo du weißt wie sie funktioniert kannst du es ja selber kurz ausprobieren. Hier schau auf diese Zeitung und verschlüssele das Wort LONDON, indem du das Schlüsselwort ABC nutzt.",
            "Kip: Lass dir ruhig Zeit. ≧◉◡◉≦ ",
            "...",
            "Kip: Sehr gut gemacht jetzt wo du verstanden hast, wie man damit Wörter Verschlüsselt, solltest du Versuchen ein Wort zu Entschlüsseln.",
            "Kip: Das Schlüsselwort ist ein Name. Mehr verrate ich erst wenn du Probleme hast. ≧◉◡◉≦",
            "...",
            "Kip: Toll du hast die Vigenère Chiffre damit vollständig verstanden. Wir haben den nächsten Schritt auf unserer Reise geschafft. ≧◉◡◉≦",
            "Kip: Unser Navigationsgerät ist leider erstmal kaputt bis unsere Reise weiter geht sollten wir erstmal zurück in die Basis!",
            "Kip: Auf durch das Portal. Folge mir einfach!!",
            "..."
            )));

        storyThree.setStoryParts(List.of(
            "Kapitel 3: London im 16ten Jahrhundert"
        ));

        storyThree.setCheckpoint("Kapitel 3: London im 16ten Jahrhundert");
        partList.add(storyThree);
        System.out.println("Added Part 3.");

        storyMode.setStoryParts(partList);
        storyMode.setPart("3");

        return storyMode;
    }

    /**
     * Gets the current story part the user is on.
     * 
     * @param StoryMode storyMode The storyMode of the user.
     * 
     * @return The current story part as an Object.
     */
    public int getCurrentStoryPart(StoryMode storyMode) {
        return Integer.parseInt(storyMode.getPart());
    }

    /**
     * Moves to the next story part if available.
     * 
     * @param StoryMode storyMode The storyMode of the user.
     * 
     * @return True if the user successfully moved to the next part, False if no more parts.
     */
    public StoryMode nextStoryPart(StoryMode storyMode) {
        try {
            int currentPart = getCurrentStoryPart(storyMode);
            System.out.println(currentPart);
            currentPart += 1;
            storyMode.setPart(Integer.toString(currentPart));
            return storyMode;

        } catch(Exception e) {
            System.out.println("An error accured returning old storyMode without switch.");
            return storyMode;
        }
    }

}
