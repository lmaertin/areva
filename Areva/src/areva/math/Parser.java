package areva.math;

import java.util.*;

/**
 * This parser analysises formulas und calculates them
 * </ul>
 *  @author Benjamin Sigg, Sven von H�veling
 *  @version 2.0
 *  https://github.com/DanielGronau/bdparser
 *  http://www.java-forum.org/allgemeines/12306-parser-fuer-mathematische-formeln.html
 */
public class Parser{   
    /**
     *  Dieses Objekt zeigt die Stellen auf, an denen eine �ffnende Klammer zu
     *  finden ist 
     */
    private static final Object OPEN = Character.valueOf( '(' );
 
    /** Dieses Objekt zeigt an, wo eine schliessende Klammer zu finden ist. */
    private static final Object CLOSE = Character.valueOf( ')' );
 
    /** Dieses Objekt stellt das trennende Komma dar */
    private static final Object SEPARATOR = Character.valueOf( ',' );
 
    /** Eine Liste aller Operationen */
    private Map<String, Operation> operations = new HashMap<String, Operation>();
 
    /** Eine Liste aller Fuktionen die benutzt werden. */
    private Map<String, Function> functions = new HashMap<String, Function>();
 
    /** Eine Liste aller Konstanten */
    private Map<String, Double> constants = new HashMap<String, Double>();
 
    /**
     * Defaultkonstruktor: f�llt die Liste der Operationen und die
     * Map der Konstanten mit +,-,*,/ bzw. "e" und "pi".
     */
    public Parser(){
        // Standard-Operationen
        addOperation( "+", new Operation(){
            public int getPriority() {
                return 1;
            }
            public double calculate( double a, double b ) {
                return a+b;
            }
        });
        
        addFunction( "+", new Function(){
            public double calculate( double[] values ) {
                return values[0];
            }
            public boolean validNrOfArguments( int count ) {
                return count == 1;
            }
        });
 
        addOperation( "-", new Operation(){
            public int getPriority() {
                return 1;
            }
            public double calculate( double a, double b ) {
                return a-b;
            }
        });
        
        addFunction( "-", new Function(){
            public double calculate( double[] values ) {
                return -values[0];
            }
            public boolean validNrOfArguments( int count ) {
                return count == 1;
            }
        });
        
        addOperation( "^", new Operation() {
        	public int getPriority() {
        		return 3;
        	}
        	
        	public double calculate(double a, double b) {
        		double result = 1;
        		for (int i = 0; i < (int) b; i++) {
        			result = result * a;
        		}
        		return result;
        	}
        });
 
        addOperation( "*", new Operation(){
            public int getPriority() {
                return 2;
            }
            public double calculate( double a, double b ) {
                return a*b;
            }
        });
 
        addOperation( "/", new Operation(){
            public int getPriority() {
                return 2;
            }
            public double calculate( double a, double b ) {
                return a/b;
            }
        });
 
        // Zus�tzliche Funktionen
        addFunction( "sqrt", new Function(){
            public double calculate( double[] values ) {
                return Math.sqrt( values[0] );
            }
 
            public boolean validNrOfArguments( int count ) {
                return count == 1;
            }
        });
        
        addFunction( "min", new Function(){
            public double calculate( double[] values ) {
                return Math.min( values[0], values[1] );
            }
            public boolean validNrOfArguments( int count ) {
                return count == 2;
            }
        });
 
        // Konstanten
        constants.put( "e", Math.E );
        constants.put( "pi", Math.PI );
    }
    
    public void addOperation( String name, Operation operation ){
        operations.put( name, operation );
    }
    
    public void addFunction( String name, Function function ){
        functions.put( name, function );
    }
 
    /**
     * Parst eine mathematische Formel.
     * @param formula Die Formel
     * @return Der Wert dieser Formel
     * @throws IllegalArgumentException Sollte der �bergebene String gar keine Formel sein.
     */
    public double parse( String formula ){
        /*
         * Es ist unpraktisch die Formel als String zu behandeln, deshalb
         * wird sie zuerst in sog. Tokens aufgeteilt. Jedes Token ist ein Object
         * welches irgendetwas darstellt, z.B. ein Double, OPEN, CLOSE, ...
         */
        List<Object> tokens = tokenize( formula );
 
        /*
         * Korrekte Anzahl Klammern und Kommas testen.
         */
        
        int count = 0;
        for( Object token : tokens ){
            if( token == OPEN )
                count++;
            if( token == CLOSE )
                count--;
            
            if( count < 0 )
                throw new IllegalArgumentException( "Schliessende Klammer mit fehlender �ffnender Klammer" );
            if( token == SEPARATOR && count == 0 )
                throw new IllegalArgumentException( "Komma ausserhalb Klammern" );
        }
 
        if( count > 0 )
            throw new IllegalArgumentException( "Schliessende Klammer fehlt" );
 
        if( count < 0 )
            throw new IllegalArgumentException( "Schliessende Klammer zuviel" );
        
        /*
         * Konstanten einsetzen
         */
        for( int i = 0, n = tokens.size(); i<n; i++ ){
            Object replacement = constants.get( tokens.get( i ) );
            if( replacement != null ){
                tokens.set( i, replacement );
            }
        }
        
        /* 
         * Die Formel parsen, das Ergebnis wird direkt in die Liste "tokens" 
         * geschrieben. 
         * Beginnt die Formel mit einem "(", wird vielleicht nicht die ganze
         * Formel geparst, deshalb wird der Parse-Algorithmus solange aufgerufen
         * bis nur noch ein Element �brig ist. 
         * Um sicherzugehen, dass der Algorithmus beendet, wird verlangt, dass 
         * bei jedem Schritt wenigstens ein Element aus der parts-Liste 
         * entfernt wird (lieber eine Exception als eine Endlosschleife...)
         */
 
        int size = tokens.size();
        while( size > 1 ){
            parse( tokens, 0 );
            if( tokens.size() >= size ){
                throw new IllegalArgumentException(
                "Die Formel wird nicht vollst�ndig geparst, evtl. wurde eine Operation vergessen?" );
            }
            size = tokens.size();
        }
 
        if( size != 1 || !(tokens.get( 0 ) instanceof Double))
            throw new IllegalArgumentException( "Unbekannter Fehler in der Formel" );
 
        return (Double)tokens.get( 0 );
    }
 
    /**
     * Parst den Teil einer Formel, der zwischen 2 Klammern steht. Dabei
     * kann es innerhalb dieses Teiles weitere Klammern haben.
 
     * Es ist auch m�glich die gesammte Formel zu �bergeben, der Algorithmus
     * arbeitet gleich, nur werden zwei "imagin�re Klammern" um die Formel
     * gepackt.
     * @param formula Die Formel
     * @param offset Der Index des ersten Zeichens der Klammer. Ist dies
     * das �ffnende Klammerzeichen muss auch ein schliessendes Klammerzeichen
     * gefunden werden. Ist dies ein anderes Element, darf sich keine schliessende
     * Klammer auf derselben Ebene befinden.
     */
    private void parse( List<Object> formula, int offset ){        
        // open gibt an, ob diese Formel echte Klammern benutzt.
        // (open=false bedeutet, dass imagin�re Klammern benutzt werden).
        boolean open = formula.get( offset ) == OPEN;
 
        if( open ){
            formula.remove( offset );
        }
        
        int begin = offset;
        int length = 0;
        
        boolean done = false;
        
        while( begin+length < formula.size() ){
            Object end = formula.get( begin+length );
            if( end == OPEN ){
                parse( formula, begin+length );
            }
            else if( end == CLOSE || end == SEPARATOR ){
                if( length == 0 )
                    throw new IllegalArgumentException( "Fehlende Ausdr�cke, z.B. leere Klammer" );
                
                parse( formula, begin, length );
                formula.remove( begin+1 );
                begin++;
                length = 0;
 
                if( end == CLOSE ){
                    done = true;
                    break;
                }
            }
            else
                length++;
        }
        
        if( !done && begin+length == formula.size() ){
            parse( formula, begin, length );
            begin++;
            length = 0;
        }
        
        if( offset+1 != begin ){
            double[] value = new double[ begin-offset ];
            for( int i = begin-1; i >= offset; i-- ){
                value[i-offset] = (Double)formula.remove( i );
                begin--;
            }
            formula.add( offset, value );
        }
    }
 
    /**
     * Parst alle Elemente in der Formel von offset bis offset+length. Es sollte kein
     * {@link #OPEN}, {@link #CLOSE} oder {@link #SEPARATOR} zwischen
     * <code>offset</code> und <code>offset+length</code> sein. Nachdem diese
     * Methode fertig ist, wurden alle Elemente bis auf eines aus dem Bereich
     * <code>offset, offset+length</code> entfernt.
     * @param formula Die Formel
     * @param offset Der Beginn der Teilformel (inklusive).
     * @param length Die Anzahl Elemente der Formel.
     */
    private void parse( List<Object> formula, int offset, int length ){
        /*
         * Die Funktionen berechnen. Funktionen erkennt man an einem
         * Merkmal: links von ihnen steht keine Zahl (da sie selbst eine Zahl
         * darstellen, muss links von ihnen eine Operation, ein Komma oder
         * eine Klammer sein)
         */
        for( int i = offset+length-2; i >= offset; i-- ){
            Function function = functions.get( formula.get( i ) );
            if( function != null ){
 
                if( i == offset || (!(formula.get( i-1 ) instanceof Double ) && !(formula.get(i-1) instanceof double[]))){
                    Object arguments = formula.get( i+1 );
                    double[] values = null;
                    if( arguments instanceof Double ){
                        values = new double[]{ (Double)arguments };
                    }
                    else if( arguments instanceof double[] ){
                        values = (double[])arguments;
                    }
                    else{
                        throw new IllegalArgumentException( "Fehlende Argumente f�r " + function + 
                           ", gefunden: " + arguments );
                    }
                    
                    if( !function.validNrOfArguments( values.length ))
                        throw new IllegalArgumentException( "Falsche Anzahl Argumente f�r " + function + 
                           ", gefunden: " + values.length );
                    
                    formula.remove( i+1 );
                    formula.set( i, function.calculate( values ) );
                    length--;
                }
            }
        }
        
        /*
         * Verbleibende Strings durch Operationen ersetzen
         */
        for( int i = offset; i < offset+length; i++ ){
            Object check = formula.get( i );
            if( check instanceof String ){
                Operation operation = operations.get( check );
                if( operation == null ){
                    throw new IllegalArgumentException( "Element " + check + 
                       " wird wie Operation benutzt, aber eine Operation mit dem Namen gibt es nicht." );
                }
                formula.set( i, operation );
            }
        }
        
        /*
         * Nun die Operation durchgehen. Es wird jeweils die Operation mit der
         * h�chsten Priorit�t, von links her suchend, verarbeitet.
         */
        while( length > 1 ){
            int current = length;
            
            // Maximale Priorit�t suchen
            int priority = Integer.MIN_VALUE;
            for( int i = offset; i < offset+length; i++ ){
                Object check = formula.get( i );
                if( check instanceof Operation ){
                    priority = Math.max( priority, ((Operation)check).getPriority() );
                }
            }
            
            // Operationen von links her abarbeiten
            for( int i = offset+1; i < offset+length-1; i++ ){
                Object check = formula.get( i );
                if( check instanceof Operation ){
                    Operation operation = (Operation)check;
                    if( operation.getPriority() == priority ){
                        Object left = formula.get( i-1 );
                        Object right = formula.get( i+1 );
                        
                        if( !(left instanceof Double ))
                            throw new IllegalArgumentException( "Operation nicht von Zahlen umgeben" );
                        
                        if( !(right instanceof Double ))
                            throw new IllegalArgumentException( "Operation nicht von Zahlen umgeben" );
                        
                        formula.set( i, operation.calculate( (Double)left, (Double)right ) );
                        formula.remove( i+1 );
                        formula.remove( i-1 );
                        i--;
                        length -= 2;
                    }
                }
            }
            
            // Testen ob noch was passiert
            if( length == current ){
                throw new IllegalArgumentException( "Formel kann nicht aufgel�st werden" );
            }
        }
    }
 
    /**
     * Wandelt die Formel welche als Text vorliegt in eine Liste von
     * Strings, Doubles, {@link #OPEN}, {@link #CLOSE} und {@link #SEPARATOR}
     * um.
     * @param formula Die Formel
     * @return Die "Tokens" der Formel.
     */
    private List<Object> tokenize( String formula ){
        // Index des Chars, der zurzeit betrachtet wird.
        int offset = 0;
        int length = formula.length();
        List<Object> parts = new ArrayList<Object>();
 
        // Strings die eine Bedeutung haben
        Set<String> texts = new HashSet<String>();
        texts.addAll( constants.keySet() );
        texts.addAll( operations.keySet() );
        texts.addAll( functions.keySet() );
 
        while( offset < length ){
            char current = formula.charAt( offset );
 
            // Tabulator, Leerzeichen etc. interessieren nicht
            if( !Character.isWhitespace( current )){
                if( current == '(' ){
                    parts.add( OPEN );
                    offset++;
                }
                else if( current == ')' ){
                    parts.add( CLOSE );
                    offset++;
                }
                else if( current == ',' ){
                    parts.add( SEPARATOR );
                    offset++;
                }
                else if( Character.isDigit( current ) || current == '.' ){
                    // Es folgt nun eine Zahl, welche ausgelesen werden muss
                    int end = offset+1;
                    boolean pointSeen = current == '.';
 
                    while( end < length ){
                        char next = formula.charAt( end );
                        if( Character.isDigit( next ))
                            end++;
                        else if( next == '.' && !pointSeen ){
                            pointSeen = true;
                            end++;
                        }
                        else
                            break;
                    }
 
                    parts.add( Double.parseDouble( formula.substring( offset, end ) ) );
                    offset = end;
                }
                else{
                    /* 
                     * Ein Platzhalter f�r eine Operation, Funktion oder Konstante
                     * wird gelesen.
                     */
                    int bestLength = 0;
                    String best = null;
 
                    for( String check : texts ){
                        if( formula.startsWith( check, offset )){
                            if( check.length() > bestLength ){
                                bestLength = check.length();
                                best = check;
                            }
                        }
                    }
 
                    if( best == null )
                        throw new IllegalArgumentException( "An dieser Formel stimmt was nicht" );
 
                    offset += bestLength;
                    parts.add( best ); 
                }
            }
            else
                offset++;
        }
        return parts;
    }
}