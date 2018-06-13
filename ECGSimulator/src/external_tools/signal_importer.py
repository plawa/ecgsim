import xml.etree.ElementTree as ET
import os

root_dir = 'A:\\Studia\\Praca magisterska\\Disc'
source_dir = os.path.join(root_dir, 'test')
target_dir = os.path.join(root_dir, 'extracted')

def main():
	for filename in [f for f in os.listdir(source_dir) if f.endswith('txt')]:
		filepath = os.path.join(source_dir, filename)
		with open(filepath, 'r') as file:
			for line in [line for i, line in enumerate(file) if 'rytm zatokowy' in line]: # i == 1 and
				handle_signal(filename, filepath)

def handle_signal(filename, filepath):
	for lead_number in range(0, 12):
		ekg_filepath = filepath.replace('.txt', '')
		save_part = extract_lead(ekg_filepath, lead_number)
		signalpart_filename = filename.replace('ekg.txt', 'signalpart')
		print('saving: ' + signalpart_filename)
		# save_dir = os.path.join(target_dir, str(lead_number))
		# if not os.path.exists(save_dir):
			# os.makedirs(save_dir)
		# with open(os.path.join(save_dir, signalpart_filename), 'w') as to_save:
			# to_save.write(save_part)


def extract_lead(filepath, no):
	file = ET.parse(filepath)
	return file.getroot()[no][0].text
	
						
if __name__ == "__main__":
	main()