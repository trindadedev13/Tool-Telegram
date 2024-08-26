import json

# load data from the API.
with open('.github/contributors/contributors_github_temp.json', 'r') as f:
    github_data = json.load(f)

# load existing data
with open('.github/contributors/contributors_github.json', 'r') as f:
    existing_data = json.load(f)

# convert existing data to a dictionary based on login and id
existing_dict = {(user['login'], user['id']): user for user in existing_data}

# update or add API data
for user in github_data:
    key = (user['login'], user['id'])
    if key in existing_dict:
        existing_dict[key].update(user)
    else:
        existing_dict[key] = user

# convert back to a list
combined_data = list(existing_dict.values())

# save the combined json
with open('.github/contributors/contributors_combined.json', 'w') as f:
    json.dump(combined_data, f, indent=2)
    