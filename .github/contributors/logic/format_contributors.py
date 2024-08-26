import json
import sys

def format_contributors(input_file, output_file):
    with open(input_file, 'r') as infile:
        contributors = json.load(infile)

    with open(output_file, 'w') as outfile:
        outfile.write('| Contributor | Contributions | Role |\n')
        outfile.write('| ------------ | -------------- | ---- |\n')
        for contributor in contributors:
            name = contributor['login']
            contributions = contributor['contributions']
            role = contributor.get('role', 'N/A') 
            outfile.write(f'| {name} | {contributions} | {role} |\n')

if __name__ == "__main__":
    format_contributors('.github/contributors/contributors_github.json', '.github/contributors/TABLE_CONTRIBUTORS_GITHUB.md')
