import xml.etree.ElementTree as ET
import os
root_dir = 'test'

def main():
	for fn in os.listdir(root_dir):
		if fn.endswith('txt'):
			filepath = root_dir + '\\' + fn
			with open(filepath, 'r') as file:
				for i, line in enumerate(file, 1):
					if i == 2 and 'Rytm zatokowy miarowy' in line:
						ekg_filepath = filepath.replace('.txt', '')
						save_part = extract_lead_i(ekg_filepath)
						signalpart_filename = fn.replace('ekg.txt', 'signalpart')
						print('saving: ' + signalpart_filename)
						with open('extracted\\' + signalpart_filename, 'w') as save_file:
							save_file.write(save_part)

def extract_lead_i(filepath):
	file = ET.parse(filepath)
	return file.getroot()[0][0].text
	
						
if __name__ == "__main__":
	main()