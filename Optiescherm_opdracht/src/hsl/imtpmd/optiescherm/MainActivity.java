package hsl.imtpmd.optiescherm;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity
{
	private int aantalKeerGeopend;
	private String naam;
	private int kleur;
	
	
	//deze methode wordt elke keer aangeroepen als de applicatie opstart
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		
		//stel alle UI interfaces in met opgeslagen preferences (mochten die er zijn)
		
		//laad de preferences binnen
		SharedPreferences settings = ...;
		this.naam = ...;
		this.aantalKeerGeopend = ...;
		this.kleur = settings.getInt( "kleur", Color.RED );

		
		//tel aantal keer geopend op en sla op naar preferences
		this.aantalKeerGeopend += 1;
		Editor editor = ...;
		editor.putInt( ... );
		...;

		//stel de userinterface op met waarden uit de preferences
		setupUserinterface();
	}
	

	//we maken een aparte methode voor het invullen van de hoofdscherm interface
	//omdat we deze methode op twee locaties willen aanroepen: in de onCreate en in de sluitApp methoden
	public void setupUserinterface()
	{
		//setup de userinterface met de waarden uit preferences
		//naam
		TextView naamTekst = (TextView) findViewById( R.id.welkomtekst );
		naamTekst.setText( "Welkom terug " + this.naam );
		
		//aantal keer ingelogd
		TextView ingelogdTekst = (TextView) findViewById( R.id.geopendtekst );
		ingelogdTekst.setText( "U heeft dit scherm al " + this.aantalKeerGeopend + " keer geopend." );
		
		//achtergrond kleur
		LinearLayout layout = (LinearLayout) findViewById( R.id.mainlayout );
		layout.setBackgroundColor( this.kleur );
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

	//deze methoden worden door de knoppen aangeroepen en zijn gekoppeld in de XML via het onClick attribuut
	public void openOpties( View view )
	{
		//toon het optiescherm
		this.setContentView( R.layout.optiescherm );

		//stel de opties in zoals opgehaald uit de preferences
		EditText naamEditTekst = (EditText) this.findViewById( R.id.naamedittext );
		naamEditTekst.setText( this.naam );
		
		//red radioknop
		RadioButton radioRood = (RadioButton) this.findViewById( ... );
		if( ... ) { ...; }
		
		//green radioknop
		RadioButton radioGroen = ...;
		
		//blauw radioknop
		RadioButton radioBlauw = ...;
		
	}
	
	
	public void sluitApp( View view )
	{
		this.finish();
	}
	
	public void naarHoofdscherm( View view )
	{
		//haal gegevens naam en kleur op uit het optiescherm
		
		EditText naamEditTekst = (EditText) ...;
		this.naam = ...;
		
		this.kleur = Color.RED;
		RadioButton radioRood = (RadioButton) this.findViewById( R.id.radiorood );
		if( ... ) { this.kleur = Color.RED; }
		
		RadioButton radioGroen = (RadioButton) this.findViewById( R.id.radiogroen );
		if( ... ) { this.kleur = Color.GREEN; }
		
		RadioButton radioBlauw = (RadioButton) this.findViewById( R.id.radioblauw );
		if( ... ) { this.kleur = Color.BLUE; }
		
		
		//sla gegevens op in de preferences
		SharedPreferences settings = ...;
		Editor editor = ...;
		...;
		...;
		...;
		editor.commit();


		//stel userinterface opnieuw in met nieuwe waarden
		setContentView( R.layout.activity_main );
		setupUserinterface();
	}
	
	public void resetAantalKeerGeopend( View view )
	{
		//reset de aantalKeerIngelogd variable
		this.aantalKeerGeopend = ...;
	}
}
