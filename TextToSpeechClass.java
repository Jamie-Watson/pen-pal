
//getting all needed imports
import java.awt.Color;
import java.util.Locale;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

//main class featuring the text to speech method
public class TextToSpeechClass {

	//start of main
	public static void main(String[] args) {

		
		//declaring the initial foreground and background colours
		Color startingBackgroundColor = new Color(221,234,209);
		Color startingForegroundColor = new Color(79,139,85);
		
		//creating an object of the welcome window class that will allow the program to start
		welcomeWindow run = new welcomeWindow(startingBackgroundColor, startingForegroundColor);
	
	}

	//TTS function that takes in a string as a parameter that the voice will speak 
	
	public static void talk(String text) {
		
		//setting system properties that allow the use of the imported text to speech
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		
		try {
			
			//registering speech engine
			Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
			
			//creating the synthesizer which will actually speak the text given
			Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
			
			//allocating the voice
			synthesizer.allocate();
			
			//resumes output after allocating
			synthesizer.resume();		
			
			//then the synthesizer will speak the given text
			synthesizer.speakPlainText(text, null);
			
			//blocks the calling thread until the synthesizer is finished speaking
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
			
		//catch statements to ensure that there are no errors while the synthesizer is speaking
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AudioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EngineStateError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
