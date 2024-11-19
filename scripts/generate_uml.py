import argparse
import openai
import sys

def generate_uml(api_key, description, output_file):
    openai.api_key = api_key

    # GPT prompt for generating PlantUML code
    prompt = f"""
    Generate a PlantUML diagram code based on the following description:
    {description}

    Output only valid PlantUML code.
    """

    response = openai.ChatCompletion.create(
        model="gpt-4",
        messages=[
            {"role": "system", "content": "You are a UML diagram generator that outputs PlantUML code."},
            {"role": "user", "content": prompt},
        ]
    )

    # Extract the PlantUML code from the response
    uml_code = response.choices[0].message['content']

    # Save the UML code to a .puml file
    with open(output_file, 'w') as f:
        f.write(uml_code)

    print(f"Editable UML diagram saved to {output_file}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Generate UML diagram using OpenAI GPT")
    parser.add_argument("--description", required=True, help="Jira issue description")
    parser.add_argument("--output", required=True, help="Output file for UML code")
    args = parser.parse_args()

    try:
        generate_uml(api_key=sys.getenv("OPENAI_API_KEY"), description=args.description, output_file=args.output)
    except Exception as e:
        print(f"Error generating UML: {e}")
        sys.exit(1)
