import xml.etree.ElementTree as ET
import os

root_dir = 'A:\\Studia\\Praca magisterska\\Disc'
source_dir = os.path.join(root_dir, 'test')
target_dir = os.path.join(root_dir, 'extracted')


def main():
    # for filename in [f for f in os.listdir(sourc e_dir) if f.endswith('txt')]:
    filename = 'D_00111.ekg'
    filepath = os.path.join(source_dir, filename)
    with open(filepath, 'r') as file:
        # i == 1 and
        # for line in [line for i, line in enumerate(file) if 'typ: SV' in line]:
        handle_signal(filename, filepath, 432, 1432)


def handle_signal(filename, filepath, cut_from, cut_to):
    for lead_number in range(0, 12):
        ekg_filepath = filepath.replace('.txt', '')
        save_part = extract_lead(ekg_filepath, lead_number)
        cut_part = save_part.split()[cut_from:cut_to]
        signalpart_filename = filename.replace('ekg', 'ekg')
        save_dir = os.path.join(target_dir, str(lead_number))
        print('saving: ' + save_dir + '\\' + signalpart_filename)
        if not os.path.exists(save_dir):
            os.makedirs(save_dir)
        with open(os.path.join(save_dir, signalpart_filename), 'w') as to_save:
            to_save.write(' '.join(cut_part))

def extract_lead(filepath, no):
    file = ET.parse(filepath)
    return file.getroot()[no][0].text


if __name__ == "__main__":
    main()
