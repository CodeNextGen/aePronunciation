package com.aepronunciation.ipa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

public class DoubleSound {

	// private class variables
	private HashMap<String, Integer> hashMap;
	private static Random random = new Random();
	private String[] doubleSounds;

	// constructor
	public DoubleSound() {
		// this.privateClassVariable = something
		init();
	}

	// public methods to return values
	public int getCount() {

		return hashMap.size();
	}

	private static boolean stringContainsItemFromList(String inputString, ArrayList<String> items)
	{
		for(int i =0; i < items.size(); i++)
		{
			if(inputString.equals(items.get(i)))
			{
				return true;
			}
		}
		return false;
	}

	public String getRandomIpaFromAllowedSounds(Context context, ArrayList<String> allowedSounds) {
		if (doubleSounds == null) {
			doubleSounds = context.getResources().getStringArray(
					R.array.double_sounds);
		}

        if (allowedSounds.isEmpty()) {
            return getRandomIpa(context);
        }

        ArrayList<String> vowels  = new ArrayList<String>();
        ArrayList<String> consonants = new ArrayList<String>();
        for (String s: allowedSounds) {
            if (PhonemeTable.INSTANCE.isConsonant(s)) {
                consonants.add(s);
            } else {
                vowels.add(s);
            }
        }

		ArrayList<String> allowedPairs = new ArrayList<String>();
		for (String p: doubleSounds) {
            Pair<String,String> cv = PhonemeTable.INSTANCE.SplitDoubleSound(p);
            // Log.d("debug", String.format("CV: %s %s", cv.first, cv.second));
            if (!stringContainsItemFromList(cv.first, consonants) || !stringContainsItemFromList(cv.second, vowels)) {
                // Log.d("debug", String.format("not allowed: %s", p));
				continue;
			}
			allowedPairs.add(p);
		}

		// get random integer (0 <= x < numberOfSounds)
		int soundIndex = random.nextInt(allowedPairs.size());

		// translate integer to ipa string
		String ipa = allowedPairs.get(soundIndex);

		return ipa;
	}

	public String getRandomIpa(Context context) {

		// get single sounds string array
		// this does not include schwa and unstressed er
		if (doubleSounds == null) {
			doubleSounds = context.getResources().getStringArray(
					R.array.double_sounds);
		}

		// get random integer (0 <= x < numberOfSounds)
		int soundIndex = random.nextInt(doubleSounds.length);

		// translate integer to ipa string
		String ipa = doubleSounds[soundIndex];

		return ipa;
	}

	public int getSoundResourceId(String doubleSoundIpa) {

		// returns null if no value found
		if (hashMap.containsKey(doubleSoundIpa)){
			return hashMap.get(doubleSoundIpa);
		}else{
			return -1;
		}
		
	}

	// initialize the hashmap when new object created
	private void init() {

		hashMap = new HashMap<String, Integer>();

		hashMap.put("pi", R.raw.pairs01_01);
		hashMap.put("pɪ", R.raw.pairs01_02);
		hashMap.put("pe", R.raw.pairs01_03);
		hashMap.put("pɛ", R.raw.pairs01_04);
		hashMap.put("pæ", R.raw.pairs01_05);
		hashMap.put("pɑ", R.raw.pairs01_06);
		hashMap.put("pɔ", R.raw.pairs01_07);
		hashMap.put("po", R.raw.pairs01_08);
		hashMap.put("pʊ", R.raw.pairs01_09);
		hashMap.put("pu", R.raw.pairs01_10);
		hashMap.put("pʌ", R.raw.pairs01_11);
		hashMap.put("paɪ", R.raw.pairs01_12);
		hashMap.put("paʊ", R.raw.pairs01_13);
		hashMap.put("pɔɪ", R.raw.pairs01_14);
		hashMap.put("pɝ", R.raw.pairs01_15);
		hashMap.put("pɑr", R.raw.pairs01_16);
		hashMap.put("pɛr", R.raw.pairs01_17);
		hashMap.put("pɪr", R.raw.pairs01_18);
		hashMap.put("pɔr", R.raw.pairs01_19);
		hashMap.put("bi", R.raw.pairs09_01);
		hashMap.put("bɪ", R.raw.pairs09_02);
		hashMap.put("be", R.raw.pairs09_03);
		hashMap.put("bɛ", R.raw.pairs09_04);
		hashMap.put("bæ", R.raw.pairs09_05);
		hashMap.put("bɑ", R.raw.pairs09_06);
		hashMap.put("bɔ", R.raw.pairs09_07);
		hashMap.put("bo", R.raw.pairs09_08);
		hashMap.put("bʊ", R.raw.pairs09_09);
		hashMap.put("bu", R.raw.pairs09_10);
		hashMap.put("bʌ", R.raw.pairs09_11);
		hashMap.put("baɪ", R.raw.pairs09_12);
		hashMap.put("baʊ", R.raw.pairs09_13);
		hashMap.put("bɔɪ", R.raw.pairs09_14);
		hashMap.put("bɝ", R.raw.pairs09_15);
		hashMap.put("bɑr", R.raw.pairs09_16);
		hashMap.put("bɛr", R.raw.pairs09_17);
		hashMap.put("bɪr", R.raw.pairs09_18);
		hashMap.put("bɔr", R.raw.pairs09_19);
		hashMap.put("ti", R.raw.pairs02_01);
		hashMap.put("tɪ", R.raw.pairs02_02);
		hashMap.put("te", R.raw.pairs02_03);
		hashMap.put("tɛ", R.raw.pairs02_04);
		hashMap.put("tæ", R.raw.pairs02_05);
		hashMap.put("tɑ", R.raw.pairs02_06);
		hashMap.put("tɔ", R.raw.pairs02_07);
		hashMap.put("to", R.raw.pairs02_08);
		hashMap.put("tʊ", R.raw.pairs02_09);
		hashMap.put("tu", R.raw.pairs02_10);
		hashMap.put("tʌ", R.raw.pairs02_11);
		hashMap.put("taɪ", R.raw.pairs02_12);
		hashMap.put("taʊ", R.raw.pairs02_13);
		hashMap.put("tɔɪ", R.raw.pairs02_14);
		hashMap.put("tɝ", R.raw.pairs02_15);
		hashMap.put("tɑr", R.raw.pairs02_16);
		hashMap.put("tɛr", R.raw.pairs02_17);
		hashMap.put("tɪr", R.raw.pairs02_18);
		hashMap.put("tɔr", R.raw.pairs02_19);
		hashMap.put("di", R.raw.pairs10_01);
		hashMap.put("dɪ", R.raw.pairs10_02);
		hashMap.put("de", R.raw.pairs10_03);
		hashMap.put("dɛ", R.raw.pairs10_04);
		hashMap.put("dæ", R.raw.pairs10_05);
		hashMap.put("dɑ", R.raw.pairs10_06);
		hashMap.put("dɔ", R.raw.pairs10_07);
		hashMap.put("do", R.raw.pairs10_08);
		hashMap.put("dʊ", R.raw.pairs10_09);
		hashMap.put("du", R.raw.pairs10_10);
		hashMap.put("dʌ", R.raw.pairs10_11);
		hashMap.put("daɪ", R.raw.pairs10_12);
		hashMap.put("daʊ", R.raw.pairs10_13);
		hashMap.put("dɔɪ", R.raw.pairs10_14);
		hashMap.put("dɝ", R.raw.pairs10_15);
		hashMap.put("dɑr", R.raw.pairs10_16);
		hashMap.put("dɛr", R.raw.pairs10_17);
		hashMap.put("dɪr", R.raw.pairs10_18);
		hashMap.put("dɔr", R.raw.pairs10_19);
		hashMap.put("ki", R.raw.pairs04_01);
		hashMap.put("kɪ", R.raw.pairs04_02);
		hashMap.put("ke", R.raw.pairs04_03);
		hashMap.put("kɛ", R.raw.pairs04_04);
		hashMap.put("kæ", R.raw.pairs04_05);
		hashMap.put("kɑ", R.raw.pairs04_06);
		hashMap.put("kɔ", R.raw.pairs04_07);
		hashMap.put("ko", R.raw.pairs04_08);
		hashMap.put("kʊ", R.raw.pairs04_09);
		hashMap.put("ku", R.raw.pairs04_10);
		hashMap.put("kʌ", R.raw.pairs04_11);
		hashMap.put("kaɪ", R.raw.pairs04_12);
		hashMap.put("kaʊ", R.raw.pairs04_13);
		hashMap.put("kɔɪ", R.raw.pairs04_14);
		hashMap.put("kɝ", R.raw.pairs04_15);
		hashMap.put("kɑr", R.raw.pairs04_16);
		hashMap.put("kɛr", R.raw.pairs04_17);
		hashMap.put("kɪr", R.raw.pairs04_18);
		hashMap.put("kɔr", R.raw.pairs04_19);
		hashMap.put("gi", R.raw.pairs12_01);
		hashMap.put("gɪ", R.raw.pairs12_02);
		hashMap.put("ge", R.raw.pairs12_03);
		hashMap.put("gɛ", R.raw.pairs12_04);
		hashMap.put("gæ", R.raw.pairs12_05);
		hashMap.put("gɑ", R.raw.pairs12_06);
		hashMap.put("gɔ", R.raw.pairs12_07);
		hashMap.put("go", R.raw.pairs12_08);
		hashMap.put("gʊ", R.raw.pairs12_09);
		hashMap.put("gu", R.raw.pairs12_10);
		hashMap.put("gʌ", R.raw.pairs12_11);
		hashMap.put("gaɪ", R.raw.pairs12_12);
		hashMap.put("gaʊ", R.raw.pairs12_13);
		hashMap.put("gɔɪ", R.raw.pairs12_14);
		hashMap.put("gɝ", R.raw.pairs12_15);
		hashMap.put("gɑr", R.raw.pairs12_16);
		hashMap.put("gɛr", R.raw.pairs12_17);
		hashMap.put("gɪr", R.raw.pairs12_18);
		hashMap.put("gɔr", R.raw.pairs12_19);
		hashMap.put("ʧi", R.raw.pairs03_01);
		hashMap.put("ʧɪ", R.raw.pairs03_02);
		hashMap.put("ʧe", R.raw.pairs03_03);
		hashMap.put("ʧɛ", R.raw.pairs03_04);
		hashMap.put("ʧæ", R.raw.pairs03_05);
		hashMap.put("ʧɑ", R.raw.pairs03_06);
		hashMap.put("ʧɔ", R.raw.pairs03_07);
		hashMap.put("ʧo", R.raw.pairs03_08);
		hashMap.put("ʧʊ", R.raw.pairs03_09);
		hashMap.put("ʧu", R.raw.pairs03_10);
		hashMap.put("ʧʌ", R.raw.pairs03_11);
		hashMap.put("ʧaɪ", R.raw.pairs03_12);
		hashMap.put("ʧaʊ", R.raw.pairs03_13);
		hashMap.put("ʧɔɪ", R.raw.pairs03_14);
		hashMap.put("ʧɝ", R.raw.pairs03_15);
		hashMap.put("ʧɑr", R.raw.pairs03_16);
		hashMap.put("ʧɛr", R.raw.pairs03_17);
		hashMap.put("ʧɪr", R.raw.pairs03_18);
		hashMap.put("ʧɔr", R.raw.pairs03_19);
		hashMap.put("ʤi", R.raw.pairs11_01);
		hashMap.put("ʤɪ", R.raw.pairs11_02);
		hashMap.put("ʤe", R.raw.pairs11_03);
		hashMap.put("ʤɛ", R.raw.pairs11_04);
		hashMap.put("ʤæ", R.raw.pairs11_05);
		hashMap.put("ʤɑ", R.raw.pairs11_06);
		hashMap.put("ʤɔ", R.raw.pairs11_07);
		hashMap.put("ʤo", R.raw.pairs11_08);
		hashMap.put("ʤʊ", R.raw.pairs11_09);
		hashMap.put("ʤu", R.raw.pairs11_10);
		hashMap.put("ʤʌ", R.raw.pairs11_11);
		hashMap.put("ʤaɪ", R.raw.pairs11_12);
		hashMap.put("ʤaʊ", R.raw.pairs11_13);
		hashMap.put("ʤɔɪ", R.raw.pairs11_14);
		hashMap.put("ʤɝ", R.raw.pairs11_15);
		hashMap.put("ʤɑr", R.raw.pairs11_16);
		hashMap.put("ʤɛr", R.raw.pairs11_17);
		hashMap.put("ʤɪr", R.raw.pairs11_18);
		hashMap.put("ʤɔr", R.raw.pairs11_19);
		hashMap.put("fi", R.raw.pairs05_01);
		hashMap.put("fɪ", R.raw.pairs05_02);
		hashMap.put("fe", R.raw.pairs05_03);
		hashMap.put("fɛ", R.raw.pairs05_04);
		hashMap.put("fæ", R.raw.pairs05_05);
		hashMap.put("fɑ", R.raw.pairs05_06);
		hashMap.put("fɔ", R.raw.pairs05_07);
		hashMap.put("fo", R.raw.pairs05_08);
		hashMap.put("fʊ", R.raw.pairs05_09);
		hashMap.put("fu", R.raw.pairs05_10);
		hashMap.put("fʌ", R.raw.pairs05_11);
		hashMap.put("faɪ", R.raw.pairs05_12);
		hashMap.put("faʊ", R.raw.pairs05_13);
		hashMap.put("fɔɪ", R.raw.pairs05_14);
		hashMap.put("fɝ", R.raw.pairs05_15);
		hashMap.put("fɑr", R.raw.pairs05_16);
		hashMap.put("fɛr", R.raw.pairs05_17);
		hashMap.put("fɪr", R.raw.pairs05_18);
		hashMap.put("fɔr", R.raw.pairs05_19);
		hashMap.put("vi", R.raw.pairs13_01);
		hashMap.put("vɪ", R.raw.pairs13_02);
		hashMap.put("ve", R.raw.pairs13_03);
		hashMap.put("vɛ", R.raw.pairs13_04);
		hashMap.put("væ", R.raw.pairs13_05);
		hashMap.put("vɑ", R.raw.pairs13_06);
		hashMap.put("vɔ", R.raw.pairs13_07);
		hashMap.put("vo", R.raw.pairs13_08);
		hashMap.put("vʊ", R.raw.pairs13_09);
		hashMap.put("vu", R.raw.pairs13_10);
		hashMap.put("vʌ", R.raw.pairs13_11);
		hashMap.put("vaɪ", R.raw.pairs13_12);
		hashMap.put("vaʊ", R.raw.pairs13_13);
		hashMap.put("vɔɪ", R.raw.pairs13_14);
		hashMap.put("vɝ", R.raw.pairs13_15);
		hashMap.put("vɑr", R.raw.pairs13_16);
		hashMap.put("vɛr", R.raw.pairs13_17);
		hashMap.put("vɪr", R.raw.pairs13_18);
		hashMap.put("vɔr", R.raw.pairs13_19);
		hashMap.put("θi", R.raw.pairs06_01);
		hashMap.put("θɪ", R.raw.pairs06_02);
		hashMap.put("θe", R.raw.pairs06_03);
		hashMap.put("θɛ", R.raw.pairs06_04);
		hashMap.put("θæ", R.raw.pairs06_05);
		hashMap.put("θɑ", R.raw.pairs06_06);
		hashMap.put("θɔ", R.raw.pairs06_07);
		hashMap.put("θo", R.raw.pairs06_08);
		hashMap.put("θʊ", R.raw.pairs06_09);
		hashMap.put("θu", R.raw.pairs06_10);
		hashMap.put("θʌ", R.raw.pairs06_11);
		hashMap.put("θaɪ", R.raw.pairs06_12);
		hashMap.put("θaʊ", R.raw.pairs06_13);
		hashMap.put("θɔɪ", R.raw.pairs06_14);
		hashMap.put("θɝ", R.raw.pairs06_15);
		hashMap.put("θɑr", R.raw.pairs06_16);
		hashMap.put("θɛr", R.raw.pairs06_17);
		hashMap.put("θɪr", R.raw.pairs06_18);
		hashMap.put("θɔr", R.raw.pairs06_19);
		hashMap.put("ði", R.raw.pairs14_01);
		hashMap.put("ðɪ", R.raw.pairs14_02);
		hashMap.put("ðe", R.raw.pairs14_03);
		hashMap.put("ðɛ", R.raw.pairs14_04);
		hashMap.put("ðæ", R.raw.pairs14_05);
		hashMap.put("ðɑ", R.raw.pairs14_06);
		hashMap.put("ðɔ", R.raw.pairs14_07);
		hashMap.put("ðo", R.raw.pairs14_08);
		hashMap.put("ðʊ", R.raw.pairs14_09);
		hashMap.put("ðu", R.raw.pairs14_10);
		hashMap.put("ðʌ", R.raw.pairs14_11);
		hashMap.put("ðaɪ", R.raw.pairs14_12);
		hashMap.put("ðaʊ", R.raw.pairs14_13);
		hashMap.put("ðɔɪ", R.raw.pairs14_14);
		hashMap.put("ðɝ", R.raw.pairs14_15);
		hashMap.put("ðɑr", R.raw.pairs14_16);
		hashMap.put("ðɛr", R.raw.pairs14_17);
		hashMap.put("ðɪr", R.raw.pairs14_18);
		hashMap.put("ðɔr", R.raw.pairs14_19);
		hashMap.put("si", R.raw.pairs07_01);
		hashMap.put("sɪ", R.raw.pairs07_02);
		hashMap.put("se", R.raw.pairs07_03);
		hashMap.put("sɛ", R.raw.pairs07_04);
		hashMap.put("sæ", R.raw.pairs07_05);
		hashMap.put("sɑ", R.raw.pairs07_06);
		hashMap.put("sɔ", R.raw.pairs07_07);
		hashMap.put("so", R.raw.pairs07_08);
		hashMap.put("sʊ", R.raw.pairs07_09);
		hashMap.put("su", R.raw.pairs07_10);
		hashMap.put("sʌ", R.raw.pairs07_11);
		hashMap.put("saɪ", R.raw.pairs07_12);
		hashMap.put("saʊ", R.raw.pairs07_13);
		hashMap.put("sɔɪ", R.raw.pairs07_14);
		hashMap.put("sɝ", R.raw.pairs07_15);
		hashMap.put("sɑr", R.raw.pairs07_16);
		hashMap.put("sɛr", R.raw.pairs07_17);
		hashMap.put("sɪr", R.raw.pairs07_18);
		hashMap.put("sɔr", R.raw.pairs07_19);
		hashMap.put("zi", R.raw.pairs15_01);
		hashMap.put("zɪ", R.raw.pairs15_02);
		hashMap.put("ze", R.raw.pairs15_03);
		hashMap.put("zɛ", R.raw.pairs15_04);
		hashMap.put("zæ", R.raw.pairs15_05);
		hashMap.put("zɑ", R.raw.pairs15_06);
		hashMap.put("zɔ", R.raw.pairs15_07);
		hashMap.put("zo", R.raw.pairs15_08);
		hashMap.put("zʊ", R.raw.pairs15_09);
		hashMap.put("zu", R.raw.pairs15_10);
		hashMap.put("zʌ", R.raw.pairs15_11);
		hashMap.put("zaɪ", R.raw.pairs15_12);
		hashMap.put("zaʊ", R.raw.pairs15_13);
		hashMap.put("zɔɪ", R.raw.pairs15_14);
		hashMap.put("zɝ", R.raw.pairs15_15);
		hashMap.put("zɑr", R.raw.pairs15_16);
		hashMap.put("zɛr", R.raw.pairs15_17);
		hashMap.put("zɪr", R.raw.pairs15_18);
		hashMap.put("zɔr", R.raw.pairs15_19);
		hashMap.put("ʃi", R.raw.pairs08_01);
		hashMap.put("ʃɪ", R.raw.pairs08_02);
		hashMap.put("ʃe", R.raw.pairs08_03);
		hashMap.put("ʃɛ", R.raw.pairs08_04);
		hashMap.put("ʃæ", R.raw.pairs08_05);
		hashMap.put("ʃɑ", R.raw.pairs08_06);
		hashMap.put("ʃɔ", R.raw.pairs08_07);
		hashMap.put("ʃo", R.raw.pairs08_08);
		hashMap.put("ʃʊ", R.raw.pairs08_09);
		hashMap.put("ʃu", R.raw.pairs08_10);
		hashMap.put("ʃʌ", R.raw.pairs08_11);
		hashMap.put("ʃaɪ", R.raw.pairs08_12);
		hashMap.put("ʃaʊ", R.raw.pairs08_13);
		hashMap.put("ʃɔɪ", R.raw.pairs08_14);
		hashMap.put("ʃɝ", R.raw.pairs08_15);
		hashMap.put("ʃɑr", R.raw.pairs08_16);
		hashMap.put("ʃɛr", R.raw.pairs08_17);
		hashMap.put("ʃɪr", R.raw.pairs08_18);
		hashMap.put("ʃɔr", R.raw.pairs08_19);
		hashMap.put("ʒi", R.raw.pairs16_01);
		hashMap.put("ʒɪ", R.raw.pairs16_02);
		hashMap.put("ʒe", R.raw.pairs16_03);
		hashMap.put("ʒɛ", R.raw.pairs16_04);
		hashMap.put("ʒæ", R.raw.pairs16_05);
		hashMap.put("ʒɑ", R.raw.pairs16_06);
		hashMap.put("ʒɔ", R.raw.pairs16_07);
		hashMap.put("ʒo", R.raw.pairs16_08);
		hashMap.put("ʒʊ", R.raw.pairs16_09);
		hashMap.put("ʒu", R.raw.pairs16_10);
		hashMap.put("ʒʌ", R.raw.pairs16_11);
		hashMap.put("ʒaɪ", R.raw.pairs16_12);
		hashMap.put("ʒaʊ", R.raw.pairs16_13);
		hashMap.put("ʒɔɪ", R.raw.pairs16_14);
		hashMap.put("ʒɝ", R.raw.pairs16_15);
		hashMap.put("ʒɑr", R.raw.pairs16_16);
		hashMap.put("ʒɛr", R.raw.pairs16_17);
		hashMap.put("ʒɪr", R.raw.pairs16_18);
		hashMap.put("ʒɔr", R.raw.pairs16_19);
		hashMap.put("mi", R.raw.pairs17_01);
		hashMap.put("mɪ", R.raw.pairs17_02);
		hashMap.put("me", R.raw.pairs17_03);
		hashMap.put("mɛ", R.raw.pairs17_04);
		hashMap.put("mæ", R.raw.pairs17_05);
		hashMap.put("mɑ", R.raw.pairs17_06);
		hashMap.put("mɔ", R.raw.pairs17_07);
		hashMap.put("mo", R.raw.pairs17_08);
		hashMap.put("mʊ", R.raw.pairs17_09);
		hashMap.put("mu", R.raw.pairs17_10);
		hashMap.put("mʌ", R.raw.pairs17_11);
		hashMap.put("maɪ", R.raw.pairs17_12);
		hashMap.put("maʊ", R.raw.pairs17_13);
		hashMap.put("mɔɪ", R.raw.pairs17_14);
		hashMap.put("mɝ", R.raw.pairs17_15);
		hashMap.put("mɑr", R.raw.pairs17_16);
		hashMap.put("mɛr", R.raw.pairs17_17);
		hashMap.put("mɪr", R.raw.pairs17_18);
		hashMap.put("mɔr", R.raw.pairs17_19);
		hashMap.put("ni", R.raw.pairs18_01);
		hashMap.put("nɪ", R.raw.pairs18_02);
		hashMap.put("ne", R.raw.pairs18_03);
		hashMap.put("nɛ", R.raw.pairs18_04);
		hashMap.put("næ", R.raw.pairs18_05);
		hashMap.put("nɑ", R.raw.pairs18_06);
		hashMap.put("nɔ", R.raw.pairs18_07);
		hashMap.put("no", R.raw.pairs18_08);
		hashMap.put("nʊ", R.raw.pairs18_09);
		hashMap.put("nu", R.raw.pairs18_10);
		hashMap.put("nʌ", R.raw.pairs18_11);
		hashMap.put("naɪ", R.raw.pairs18_12);
		hashMap.put("naʊ", R.raw.pairs18_13);
		hashMap.put("nɔɪ", R.raw.pairs18_14);
		hashMap.put("nɝ", R.raw.pairs18_15);
		hashMap.put("nɑr", R.raw.pairs18_16);
		hashMap.put("nɛr", R.raw.pairs18_17);
		hashMap.put("nɪr", R.raw.pairs18_18);
		hashMap.put("nɔr", R.raw.pairs18_19);
		hashMap.put("li", R.raw.pairs20_01);
		hashMap.put("lɪ", R.raw.pairs20_02);
		hashMap.put("le", R.raw.pairs20_03);
		hashMap.put("lɛ", R.raw.pairs20_04);
		hashMap.put("læ", R.raw.pairs20_05);
		hashMap.put("lɑ", R.raw.pairs20_06);
		hashMap.put("lɔ", R.raw.pairs20_07);
		hashMap.put("lo", R.raw.pairs20_08);
		hashMap.put("lʊ", R.raw.pairs20_09);
		hashMap.put("lu", R.raw.pairs20_10);
		hashMap.put("lʌ", R.raw.pairs20_11);
		hashMap.put("laɪ", R.raw.pairs20_12);
		hashMap.put("laʊ", R.raw.pairs20_13);
		hashMap.put("lɔɪ", R.raw.pairs20_14);
		hashMap.put("lɝ", R.raw.pairs20_15);
		hashMap.put("lɑr", R.raw.pairs20_16);
		hashMap.put("lɛr", R.raw.pairs20_17);
		hashMap.put("lɪr", R.raw.pairs20_18);
		hashMap.put("lɔr", R.raw.pairs20_19);
		hashMap.put("wi", R.raw.pairs22_01);
		hashMap.put("wɪ", R.raw.pairs22_02);
		hashMap.put("we", R.raw.pairs22_03);
		hashMap.put("wɛ", R.raw.pairs22_04);
		hashMap.put("wæ", R.raw.pairs22_05);
		hashMap.put("wɑ", R.raw.pairs22_06);
		hashMap.put("wɔ", R.raw.pairs22_07);
		hashMap.put("wo", R.raw.pairs22_08);
		hashMap.put("wʊ", R.raw.pairs22_09);
		hashMap.put("wu", R.raw.pairs22_10);
		hashMap.put("wʌ", R.raw.pairs22_11);
		hashMap.put("waɪ", R.raw.pairs22_12);
		hashMap.put("waʊ", R.raw.pairs22_13);
		hashMap.put("wɔɪ", R.raw.pairs22_14);
		hashMap.put("wɝ", R.raw.pairs22_15);
		hashMap.put("wɑr", R.raw.pairs22_16);
		hashMap.put("wɛr", R.raw.pairs22_17);
		hashMap.put("wɪr", R.raw.pairs22_18);
		hashMap.put("wɔr", R.raw.pairs22_19);
		hashMap.put("ji", R.raw.pairs23_01);
		hashMap.put("jɪ", R.raw.pairs23_02);
		hashMap.put("je", R.raw.pairs23_03);
		hashMap.put("jɛ", R.raw.pairs23_04);
		hashMap.put("jæ", R.raw.pairs23_05);
		hashMap.put("jɑ", R.raw.pairs23_06);
		hashMap.put("jɔ", R.raw.pairs23_07);
		hashMap.put("jo", R.raw.pairs23_08);
		hashMap.put("jʊ", R.raw.pairs23_09);
		hashMap.put("ju", R.raw.pairs23_10);
		hashMap.put("jʌ", R.raw.pairs23_11);
		hashMap.put("jaɪ", R.raw.pairs23_12);
		hashMap.put("jaʊ", R.raw.pairs23_13);
		hashMap.put("jɔɪ", R.raw.pairs23_14);
		hashMap.put("jɝ", R.raw.pairs23_15);
		hashMap.put("jɑr", R.raw.pairs23_16);
		hashMap.put("jɛr", R.raw.pairs23_17);
		hashMap.put("jɪr", R.raw.pairs23_18);
		hashMap.put("jɔr", R.raw.pairs23_19);
		hashMap.put("hi", R.raw.pairs19_01);
		hashMap.put("hɪ", R.raw.pairs19_02);
		hashMap.put("he", R.raw.pairs19_03);
		hashMap.put("hɛ", R.raw.pairs19_04);
		hashMap.put("hæ", R.raw.pairs19_05);
		hashMap.put("hɑ", R.raw.pairs19_06);
		hashMap.put("hɔ", R.raw.pairs19_07);
		hashMap.put("ho", R.raw.pairs19_08);
		hashMap.put("hʊ", R.raw.pairs19_09);
		hashMap.put("hu", R.raw.pairs19_10);
		hashMap.put("hʌ", R.raw.pairs19_11);
		hashMap.put("haɪ", R.raw.pairs19_12);
		hashMap.put("haʊ", R.raw.pairs19_13);
		hashMap.put("hɔɪ", R.raw.pairs19_14);
		hashMap.put("hɝ", R.raw.pairs19_15);
		hashMap.put("hɑr", R.raw.pairs19_16);
		hashMap.put("hɛr", R.raw.pairs19_17);
		hashMap.put("hɪr", R.raw.pairs19_18);
		hashMap.put("hɔr", R.raw.pairs19_19);
		hashMap.put("ri", R.raw.pairs21_01);
		hashMap.put("rɪ", R.raw.pairs21_02);
		hashMap.put("re", R.raw.pairs21_03);
		hashMap.put("rɛ", R.raw.pairs21_04);
		hashMap.put("ræ", R.raw.pairs21_05);
		hashMap.put("rɑ", R.raw.pairs21_06);
		hashMap.put("rɔ", R.raw.pairs21_07);
		hashMap.put("ro", R.raw.pairs21_08);
		hashMap.put("rʊ", R.raw.pairs21_09);
		hashMap.put("ru", R.raw.pairs21_10);
		hashMap.put("rʌ", R.raw.pairs21_11);
		hashMap.put("raɪ", R.raw.pairs21_12);
		hashMap.put("raʊ", R.raw.pairs21_13);
		hashMap.put("rɔɪ", R.raw.pairs21_14);
		hashMap.put("rɝ", R.raw.pairs21_15);
		hashMap.put("rɑr", R.raw.pairs21_16);
		hashMap.put("rɛr", R.raw.pairs21_17);
		hashMap.put("rɪr", R.raw.pairs21_18);
		hashMap.put("rɔr", R.raw.pairs21_19);
		hashMap.put("ip", R.raw.pairs24_01);
		hashMap.put("ɪp", R.raw.pairs24_02);
		hashMap.put("ep", R.raw.pairs24_03);
		hashMap.put("ɛp", R.raw.pairs24_04);
		hashMap.put("æp", R.raw.pairs24_05);
		hashMap.put("ɑp", R.raw.pairs24_06);
		hashMap.put("ɔp", R.raw.pairs24_07);
		hashMap.put("op", R.raw.pairs24_08);
		hashMap.put("ʊp", R.raw.pairs24_09);
		hashMap.put("up", R.raw.pairs24_10);
		hashMap.put("ʌp", R.raw.pairs24_11);
		hashMap.put("aɪp", R.raw.pairs24_12);
		hashMap.put("aʊp", R.raw.pairs24_13);
		hashMap.put("ɔɪp", R.raw.pairs24_14);
		hashMap.put("ɝp", R.raw.pairs24_15);
		hashMap.put("ɑrp", R.raw.pairs24_16);
		hashMap.put("ɛrp", R.raw.pairs24_17);
		hashMap.put("ɪrp", R.raw.pairs24_18);
		hashMap.put("ɔrp", R.raw.pairs24_19);
		hashMap.put("ib", R.raw.pairs32_01);
		hashMap.put("ɪb", R.raw.pairs32_02);
		hashMap.put("eb", R.raw.pairs32_03);
		hashMap.put("ɛb", R.raw.pairs32_04);
		hashMap.put("æb", R.raw.pairs32_05);
		hashMap.put("ɑb", R.raw.pairs32_06);
		hashMap.put("ɔb", R.raw.pairs32_07);
		hashMap.put("ob", R.raw.pairs32_08);
		hashMap.put("ʊb", R.raw.pairs32_09);
		hashMap.put("ub", R.raw.pairs32_10);
		hashMap.put("ʌb", R.raw.pairs32_11);
		hashMap.put("aɪb", R.raw.pairs32_12);
		hashMap.put("aʊb", R.raw.pairs32_13);
		hashMap.put("ɔɪb", R.raw.pairs32_14);
		hashMap.put("ɝb", R.raw.pairs32_15);
		hashMap.put("ɑrb", R.raw.pairs32_16);
		hashMap.put("ɛrb", R.raw.pairs32_17);
		hashMap.put("ɪrb", R.raw.pairs32_18);
		hashMap.put("ɔrb", R.raw.pairs32_19);
		hashMap.put("it", R.raw.pairs25_01);
		hashMap.put("ɪt", R.raw.pairs25_02);
		hashMap.put("et", R.raw.pairs25_03);
		hashMap.put("ɛt", R.raw.pairs25_04);
		hashMap.put("æt", R.raw.pairs25_05);
		hashMap.put("ɑt", R.raw.pairs25_06);
		hashMap.put("ɔt", R.raw.pairs25_07);
		hashMap.put("ot", R.raw.pairs25_08);
		hashMap.put("ʊt", R.raw.pairs25_09);
		hashMap.put("ut", R.raw.pairs25_10);
		hashMap.put("ʌt", R.raw.pairs25_11);
		hashMap.put("aɪt", R.raw.pairs25_12);
		hashMap.put("aʊt", R.raw.pairs25_13);
		hashMap.put("ɔɪt", R.raw.pairs25_14);
		hashMap.put("ɝt", R.raw.pairs25_15);
		hashMap.put("ɑrt", R.raw.pairs25_16);
		hashMap.put("ɛrt", R.raw.pairs25_17);
		hashMap.put("ɪrt", R.raw.pairs25_18);
		hashMap.put("ɔrt", R.raw.pairs25_19);
		hashMap.put("id", R.raw.pairs33_01);
		hashMap.put("ɪd", R.raw.pairs33_02);
		hashMap.put("ed", R.raw.pairs33_03);
		hashMap.put("ɛd", R.raw.pairs33_04);
		hashMap.put("æd", R.raw.pairs33_05);
		hashMap.put("ɑd", R.raw.pairs33_06);
		hashMap.put("ɔd", R.raw.pairs33_07);
		hashMap.put("od", R.raw.pairs33_08);
		hashMap.put("ʊd", R.raw.pairs33_09);
		hashMap.put("ud", R.raw.pairs33_10);
		hashMap.put("ʌd", R.raw.pairs33_11);
		hashMap.put("aɪd", R.raw.pairs33_12);
		hashMap.put("aʊd", R.raw.pairs33_13);
		hashMap.put("ɔɪd", R.raw.pairs33_14);
		hashMap.put("ɝd", R.raw.pairs33_15);
		hashMap.put("ɑrd", R.raw.pairs33_16);
		hashMap.put("ɛrd", R.raw.pairs33_17);
		hashMap.put("ɪrd", R.raw.pairs33_18);
		hashMap.put("ɔrd", R.raw.pairs33_19);
		hashMap.put("ik", R.raw.pairs27_01);
		hashMap.put("ɪk", R.raw.pairs27_02);
		hashMap.put("ek", R.raw.pairs27_03);
		hashMap.put("ɛk", R.raw.pairs27_04);
		hashMap.put("æk", R.raw.pairs27_05);
		hashMap.put("ɑk", R.raw.pairs27_06);
		hashMap.put("ɔk", R.raw.pairs27_07);
		hashMap.put("ok", R.raw.pairs27_08);
		hashMap.put("ʊk", R.raw.pairs27_09);
		hashMap.put("uk", R.raw.pairs27_10);
		hashMap.put("ʌk", R.raw.pairs27_11);
		hashMap.put("aɪk", R.raw.pairs27_12);
		hashMap.put("aʊk", R.raw.pairs27_13);
		hashMap.put("ɔɪk", R.raw.pairs27_14);
		hashMap.put("ɝk", R.raw.pairs27_15);
		hashMap.put("ɑrk", R.raw.pairs27_16);
		hashMap.put("ɛrk", R.raw.pairs27_17);
		hashMap.put("ɪrk", R.raw.pairs27_18);
		hashMap.put("ɔrk", R.raw.pairs27_19);
		hashMap.put("ig", R.raw.pairs35_01);
		hashMap.put("ɪg", R.raw.pairs35_02);
		hashMap.put("eg", R.raw.pairs35_03);
		hashMap.put("ɛg", R.raw.pairs35_04);
		hashMap.put("æg", R.raw.pairs35_05);
		hashMap.put("ɑg", R.raw.pairs35_06);
		hashMap.put("ɔg", R.raw.pairs35_07);
		hashMap.put("og", R.raw.pairs35_08);
		hashMap.put("ʊg", R.raw.pairs35_09);
		hashMap.put("ug", R.raw.pairs35_10);
		hashMap.put("ʌg", R.raw.pairs35_11);
		hashMap.put("aɪg", R.raw.pairs35_12);
		hashMap.put("aʊg", R.raw.pairs35_13);
		hashMap.put("ɔɪg", R.raw.pairs35_14);
		hashMap.put("ɝg", R.raw.pairs35_15);
		hashMap.put("ɑrg", R.raw.pairs35_16);
		hashMap.put("ɛrg", R.raw.pairs35_17);
		hashMap.put("ɪrg", R.raw.pairs35_18);
		hashMap.put("ɔrg", R.raw.pairs35_19);
		hashMap.put("iʧ", R.raw.pairs26_01);
		hashMap.put("ɪʧ", R.raw.pairs26_02);
		hashMap.put("eʧ", R.raw.pairs26_03);
		hashMap.put("ɛʧ", R.raw.pairs26_04);
		hashMap.put("æʧ", R.raw.pairs26_05);
		hashMap.put("ɑʧ", R.raw.pairs26_06);
		hashMap.put("ɔʧ", R.raw.pairs26_07);
		hashMap.put("oʧ", R.raw.pairs26_08);
		hashMap.put("ʊʧ", R.raw.pairs26_09);
		hashMap.put("uʧ", R.raw.pairs26_10);
		hashMap.put("ʌʧ", R.raw.pairs26_11);
		hashMap.put("aɪʧ", R.raw.pairs26_12);
		hashMap.put("aʊʧ", R.raw.pairs26_13);
		hashMap.put("ɔɪʧ", R.raw.pairs26_14);
		hashMap.put("ɝʧ", R.raw.pairs26_15);
		hashMap.put("ɑrʧ", R.raw.pairs26_16);
		hashMap.put("ɛrʧ", R.raw.pairs26_17);
		hashMap.put("ɪrʧ", R.raw.pairs26_18);
		hashMap.put("ɔrʧ", R.raw.pairs26_19);
		hashMap.put("iʤ", R.raw.pairs34_01);
		hashMap.put("ɪʤ", R.raw.pairs34_02);
		hashMap.put("eʤ", R.raw.pairs34_03);
		hashMap.put("ɛʤ", R.raw.pairs34_04);
		hashMap.put("æʤ", R.raw.pairs34_05);
		hashMap.put("ɑʤ", R.raw.pairs34_06);
		hashMap.put("ɔʤ", R.raw.pairs34_07);
		hashMap.put("oʤ", R.raw.pairs34_08);
		hashMap.put("ʊʤ", R.raw.pairs34_09);
		hashMap.put("uʤ", R.raw.pairs34_10);
		hashMap.put("ʌʤ", R.raw.pairs34_11);
		hashMap.put("aɪʤ", R.raw.pairs34_12);
		hashMap.put("aʊʤ", R.raw.pairs34_13);
		hashMap.put("ɔɪʤ", R.raw.pairs34_14);
		hashMap.put("ɝʤ", R.raw.pairs34_15);
		hashMap.put("ɑrʤ", R.raw.pairs34_16);
		hashMap.put("ɛrʤ", R.raw.pairs34_17);
		hashMap.put("ɪrʤ", R.raw.pairs34_18);
		hashMap.put("ɔrʤ", R.raw.pairs34_19);
		hashMap.put("if", R.raw.pairs28_01);
		hashMap.put("ɪf", R.raw.pairs28_02);
		hashMap.put("ef", R.raw.pairs28_03);
		hashMap.put("ɛf", R.raw.pairs28_04);
		hashMap.put("æf", R.raw.pairs28_05);
		hashMap.put("ɑf", R.raw.pairs28_06);
		hashMap.put("ɔf", R.raw.pairs28_07);
		hashMap.put("of", R.raw.pairs28_08);
		hashMap.put("ʊf", R.raw.pairs28_09);
		hashMap.put("uf", R.raw.pairs28_10);
		hashMap.put("ʌf", R.raw.pairs28_11);
		hashMap.put("aɪf", R.raw.pairs28_12);
		hashMap.put("aʊf", R.raw.pairs28_13);
		hashMap.put("ɔɪf", R.raw.pairs28_14);
		hashMap.put("ɝf", R.raw.pairs28_15);
		hashMap.put("ɑrf", R.raw.pairs28_16);
		hashMap.put("ɛrf", R.raw.pairs28_17);
		hashMap.put("ɪrf", R.raw.pairs28_18);
		hashMap.put("ɔrf", R.raw.pairs28_19);
		hashMap.put("iv", R.raw.pairs36_01);
		hashMap.put("ɪv", R.raw.pairs36_02);
		hashMap.put("ev", R.raw.pairs36_03);
		hashMap.put("ɛv", R.raw.pairs36_04);
		hashMap.put("æv", R.raw.pairs36_05);
		hashMap.put("ɑv", R.raw.pairs36_06);
		hashMap.put("ɔv", R.raw.pairs36_07);
		hashMap.put("ov", R.raw.pairs36_08);
		hashMap.put("ʊv", R.raw.pairs36_09);
		hashMap.put("uv", R.raw.pairs36_10);
		hashMap.put("ʌv", R.raw.pairs36_11);
		hashMap.put("aɪv", R.raw.pairs36_12);
		hashMap.put("aʊv", R.raw.pairs36_13);
		hashMap.put("ɔɪv", R.raw.pairs36_14);
		hashMap.put("ɝv", R.raw.pairs36_15);
		hashMap.put("ɑrv", R.raw.pairs36_16);
		hashMap.put("ɛrv", R.raw.pairs36_17);
		hashMap.put("ɪrv", R.raw.pairs36_18);
		hashMap.put("ɔrv", R.raw.pairs36_19);
		hashMap.put("iθ", R.raw.pairs29_01);
		hashMap.put("ɪθ", R.raw.pairs29_02);
		hashMap.put("eθ", R.raw.pairs29_03);
		hashMap.put("ɛθ", R.raw.pairs29_04);
		hashMap.put("æθ", R.raw.pairs29_05);
		hashMap.put("ɑθ", R.raw.pairs29_06);
		hashMap.put("ɔθ", R.raw.pairs29_07);
		hashMap.put("oθ", R.raw.pairs29_08);
		hashMap.put("ʊθ", R.raw.pairs29_09);
		hashMap.put("uθ", R.raw.pairs29_10);
		hashMap.put("ʌθ", R.raw.pairs29_11);
		hashMap.put("aɪθ", R.raw.pairs29_12);
		hashMap.put("aʊθ", R.raw.pairs29_13);
		hashMap.put("ɔɪθ", R.raw.pairs29_14);
		hashMap.put("ɝθ", R.raw.pairs29_15);
		hashMap.put("ɑrθ", R.raw.pairs29_16);
		hashMap.put("ɛrθ", R.raw.pairs29_17);
		hashMap.put("ɪrθ", R.raw.pairs29_18);
		hashMap.put("ɔrθ", R.raw.pairs29_19);
		hashMap.put("ið", R.raw.pairs37_01);
		hashMap.put("ɪð", R.raw.pairs37_02);
		hashMap.put("eð", R.raw.pairs37_03);
		hashMap.put("ɛð", R.raw.pairs37_04);
		hashMap.put("æð", R.raw.pairs37_05);
		hashMap.put("ɑð", R.raw.pairs37_06);
		hashMap.put("ɔð", R.raw.pairs37_07);
		hashMap.put("oð", R.raw.pairs37_08);
		hashMap.put("ʊð", R.raw.pairs37_09);
		hashMap.put("uð", R.raw.pairs37_10);
		hashMap.put("ʌð", R.raw.pairs37_11);
		hashMap.put("aɪð", R.raw.pairs37_12);
		hashMap.put("aʊð", R.raw.pairs37_13);
		hashMap.put("ɔɪð", R.raw.pairs37_14);
		hashMap.put("ɝð", R.raw.pairs37_15);
		hashMap.put("ɑrð", R.raw.pairs37_16);
		hashMap.put("ɛrð", R.raw.pairs37_17);
		hashMap.put("ɪrð", R.raw.pairs37_18);
		hashMap.put("ɔrð", R.raw.pairs37_19);
		hashMap.put("is", R.raw.pairs30_01);
		hashMap.put("ɪs", R.raw.pairs30_02);
		hashMap.put("es", R.raw.pairs30_03);
		hashMap.put("ɛs", R.raw.pairs30_04);
		hashMap.put("æs", R.raw.pairs30_05);
		hashMap.put("ɑs", R.raw.pairs30_06);
		hashMap.put("ɔs", R.raw.pairs30_07);
		hashMap.put("os", R.raw.pairs30_08);
		hashMap.put("ʊs", R.raw.pairs30_09);
		hashMap.put("us", R.raw.pairs30_10);
		hashMap.put("ʌs", R.raw.pairs30_11);
		hashMap.put("aɪs", R.raw.pairs30_12);
		hashMap.put("aʊs", R.raw.pairs30_13);
		hashMap.put("ɔɪs", R.raw.pairs30_14);
		hashMap.put("ɝs", R.raw.pairs30_15);
		hashMap.put("ɑrs", R.raw.pairs30_16);
		hashMap.put("ɛrs", R.raw.pairs30_17);
		hashMap.put("ɪrs", R.raw.pairs30_18);
		hashMap.put("ɔrs", R.raw.pairs30_19);
		hashMap.put("iz", R.raw.pairs38_01);
		hashMap.put("ɪz", R.raw.pairs38_02);
		hashMap.put("ez", R.raw.pairs38_03);
		hashMap.put("ɛz", R.raw.pairs38_04);
		hashMap.put("æz", R.raw.pairs38_05);
		hashMap.put("ɑz", R.raw.pairs38_06);
		hashMap.put("ɔz", R.raw.pairs38_07);
		hashMap.put("oz", R.raw.pairs38_08);
		hashMap.put("ʊz", R.raw.pairs38_09);
		hashMap.put("uz", R.raw.pairs38_10);
		hashMap.put("ʌz", R.raw.pairs38_11);
		hashMap.put("aɪz", R.raw.pairs38_12);
		hashMap.put("aʊz", R.raw.pairs38_13);
		hashMap.put("ɔɪz", R.raw.pairs38_14);
		hashMap.put("ɝz", R.raw.pairs38_15);
		hashMap.put("ɑrz", R.raw.pairs38_16);
		hashMap.put("ɛrz", R.raw.pairs38_17);
		hashMap.put("ɪrz", R.raw.pairs38_18);
		hashMap.put("ɔrz", R.raw.pairs38_19);
		hashMap.put("iʃ", R.raw.pairs31_01);
		hashMap.put("ɪʃ", R.raw.pairs31_02);
		hashMap.put("eʃ", R.raw.pairs31_03);
		hashMap.put("ɛʃ", R.raw.pairs31_04);
		hashMap.put("æʃ", R.raw.pairs31_05);
		hashMap.put("ɑʃ", R.raw.pairs31_06);
		hashMap.put("ɔʃ", R.raw.pairs31_07);
		hashMap.put("oʃ", R.raw.pairs31_08);
		hashMap.put("ʊʃ", R.raw.pairs31_09);
		hashMap.put("uʃ", R.raw.pairs31_10);
		hashMap.put("ʌʃ", R.raw.pairs31_11);
		hashMap.put("aɪʃ", R.raw.pairs31_12);
		hashMap.put("aʊʃ", R.raw.pairs31_13);
		hashMap.put("ɔɪʃ", R.raw.pairs31_14);
		hashMap.put("ɝʃ", R.raw.pairs31_15);
		hashMap.put("ɑrʃ", R.raw.pairs31_16);
		hashMap.put("ɛrʃ", R.raw.pairs31_17);
		hashMap.put("ɪrʃ", R.raw.pairs31_18);
		hashMap.put("ɔrʃ", R.raw.pairs31_19);
		hashMap.put("iʒ", R.raw.pairs39_01);
		hashMap.put("ɪʒ", R.raw.pairs39_02);
		hashMap.put("eʒ", R.raw.pairs39_03);
		hashMap.put("ɛʒ", R.raw.pairs39_04);
		hashMap.put("æʒ", R.raw.pairs39_05);
		hashMap.put("ɑʒ", R.raw.pairs39_06);
		hashMap.put("ɔʒ", R.raw.pairs39_07);
		hashMap.put("oʒ", R.raw.pairs39_08);
		hashMap.put("ʊʒ", R.raw.pairs39_09);
		hashMap.put("uʒ", R.raw.pairs39_10);
		hashMap.put("ʌʒ", R.raw.pairs39_11);
		hashMap.put("aɪʒ", R.raw.pairs39_12);
		hashMap.put("aʊʒ", R.raw.pairs39_13);
		hashMap.put("ɔɪʒ", R.raw.pairs39_14);
		hashMap.put("ɝʒ", R.raw.pairs39_15);
		hashMap.put("ɑrʒ", R.raw.pairs39_16);
		hashMap.put("ɛrʒ", R.raw.pairs39_17);
		hashMap.put("ɪrʒ", R.raw.pairs39_18);
		hashMap.put("ɔrʒ", R.raw.pairs39_19);
		hashMap.put("im", R.raw.pairs40_01);
		hashMap.put("ɪm", R.raw.pairs40_02);
		hashMap.put("em", R.raw.pairs40_03);
		hashMap.put("ɛm", R.raw.pairs40_04);
		hashMap.put("æm", R.raw.pairs40_05);
		hashMap.put("ɑm", R.raw.pairs40_06);
		hashMap.put("ɔm", R.raw.pairs40_07);
		hashMap.put("om", R.raw.pairs40_08);
		hashMap.put("ʊm", R.raw.pairs40_09);
		hashMap.put("um", R.raw.pairs40_10);
		hashMap.put("ʌm", R.raw.pairs40_11);
		hashMap.put("aɪm", R.raw.pairs40_12);
		hashMap.put("aʊm", R.raw.pairs40_13);
		hashMap.put("ɔɪm", R.raw.pairs40_14);
		hashMap.put("ɝm", R.raw.pairs40_15);
		hashMap.put("ɑrm", R.raw.pairs40_16);
		hashMap.put("ɛrm", R.raw.pairs40_17);
		hashMap.put("ɪrm", R.raw.pairs40_18);
		hashMap.put("ɔrm", R.raw.pairs40_19);
		hashMap.put("in", R.raw.pairs41_01);
		hashMap.put("ɪn", R.raw.pairs41_02);
		hashMap.put("en", R.raw.pairs41_03);
		hashMap.put("ɛn", R.raw.pairs41_04);
		hashMap.put("æn", R.raw.pairs41_05);
		hashMap.put("ɑn", R.raw.pairs41_06);
		hashMap.put("ɔn", R.raw.pairs41_07);
		hashMap.put("on", R.raw.pairs41_08);
		hashMap.put("ʊn", R.raw.pairs41_09);
		hashMap.put("un", R.raw.pairs41_10);
		hashMap.put("ʌn", R.raw.pairs41_11);
		hashMap.put("aɪn", R.raw.pairs41_12);
		hashMap.put("aʊn", R.raw.pairs41_13);
		hashMap.put("ɔɪn", R.raw.pairs41_14);
		hashMap.put("ɝn", R.raw.pairs41_15);
		hashMap.put("ɑrn", R.raw.pairs41_16);
		hashMap.put("ɛrn", R.raw.pairs41_17);
		hashMap.put("ɪrn", R.raw.pairs41_18);
		hashMap.put("ɔrn", R.raw.pairs41_19);
		hashMap.put("iŋ", R.raw.pairs42_01);
		hashMap.put("ɪŋ", R.raw.pairs42_02);
		hashMap.put("eŋ", R.raw.pairs42_03);
		hashMap.put("ɛŋ", R.raw.pairs42_04);
		hashMap.put("æŋ", R.raw.pairs42_05);
		hashMap.put("ɑŋ", R.raw.pairs42_06);
		hashMap.put("ɔŋ", R.raw.pairs42_07);
		hashMap.put("oŋ", R.raw.pairs42_08);
		hashMap.put("ʊŋ", R.raw.pairs42_09);
		hashMap.put("uŋ", R.raw.pairs42_10);
		hashMap.put("ʌŋ", R.raw.pairs42_11);
		hashMap.put("aɪŋ", R.raw.pairs42_12);
		hashMap.put("aʊŋ", R.raw.pairs42_13);
		hashMap.put("ɔɪŋ", R.raw.pairs42_14);
		hashMap.put("ɝŋ", R.raw.pairs42_15);
		hashMap.put("ɑrŋ", R.raw.pairs42_16);
		hashMap.put("ɛrŋ", R.raw.pairs42_17);
		hashMap.put("ɪrŋ", R.raw.pairs42_18);
		hashMap.put("ɔrŋ", R.raw.pairs42_19);
		hashMap.put("il", R.raw.pairs43_01);
		hashMap.put("ɪl", R.raw.pairs43_02);
		hashMap.put("el", R.raw.pairs43_03);
		hashMap.put("ɛl", R.raw.pairs43_04);
		hashMap.put("æl", R.raw.pairs43_05);
		hashMap.put("ɑl", R.raw.pairs43_06);
		hashMap.put("ɔl", R.raw.pairs43_07);
		hashMap.put("ol", R.raw.pairs43_08);
		hashMap.put("ʊl", R.raw.pairs43_09);
		hashMap.put("ul", R.raw.pairs43_10);
		hashMap.put("ʌl", R.raw.pairs43_11);
		hashMap.put("aɪl", R.raw.pairs43_12);
		hashMap.put("aʊl", R.raw.pairs43_13);
		hashMap.put("ɔɪl", R.raw.pairs43_14);
		hashMap.put("ɝl", R.raw.pairs43_15);
		hashMap.put("ɑrl", R.raw.pairs43_16);
		hashMap.put("ɛrl", R.raw.pairs43_17);
		hashMap.put("ɪrl", R.raw.pairs43_18);
		hashMap.put("ɔrl", R.raw.pairs43_19);

	}

}
