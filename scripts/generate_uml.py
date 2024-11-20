import argparse
from openai import OpenAI
import os

def generate_uml(api_key, description, output_file, uml_format="plantuml"):
    """
    Generate UML notation based on a textual description.

    Args:
        api_key (str): OpenAI API key.
        description (str): Jira issue description or any textual input.
        output_file (str): Path to save the generated UML notation.
        uml_format (str): The desired UML format (default: plantuml).
    """
    # Initialize the OpenAI client
    client = OpenAI(
                api_key=oai_api_key,  # this is also the default, it can be omitted
                )

    # Select prompt based on desired format
    if uml_format.lower() == "plantuml":
        prompt = f"Create a UML class diagram in PlantUML notation based on the following description:\n{description}\n\nOutput only valid PlantUML code."
    elif uml_format.lower() == "mermaid":
        prompt = f"Create a UML class diagram in Mermaid.js notation based on the following description:\n{description}\n\nOutput only valid Mermaid.js code."
    else:
        raise ValueError(f"Unsupported UML format: {uml_format}")

    try:
        # Send request to OpenAI (updated syntax)
        response = client.chat.completions.create(
            model="gpt-4o-mini",  # Use gpt-3.5-turbo if gpt-4 is unavailable
            messages=[
                {"role": "system", "content": "You are a UML diagram generator."},
                {"role": "user", "content": prompt},
            ]
        )
        # Extract the UML notation from the response
        uml_code = response.choices[0].message.content

        # Save the UML code to the output file
        with open(output_file, "w") as f:
            f.write(uml_code)

        print(f"UML notation saved to {output_file}")

    except Exception as e:
        print(f"Error generating UML: {e}")
        raise

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Generate UML diagram notation using OpenAI GPT")
    parser.add_argument("--description", required=True, help="Description of the system to generate the UML for")
    parser.add_argument("--output", required=True, help="Output file for UML notation")
    parser.add_argument("--format", default="plantuml", choices=["plantuml", "mermaid"], help="UML format: plantuml or mermaid")
    args = parser.parse_args()

    try:
        generate_uml(
            oai_api_key=os.getenv("OPENAI_API_KEY"),
            description=args.description,
            output_file=args.output,
            uml_format=args.format,
        )
    except Exception as e:
        print(f"Script execution failed: {e}")
