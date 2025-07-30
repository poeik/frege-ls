# Frege Language Server

This is the repository of an implementation of the [Language Server Protocol](https://microsoft.github.io/language-server-protocol/) for
[Frege](https://github.com/Frege/frege).

## Usage

- Use the [IntelliJ plugin](https://github.com/poeik/FregeIntellIJPlugin)
- Or integrate it with NeoVim like described [here](#integrating-with-an-ide).

> Note 1: Make sure your Frege files are located in `./src/main/frege` or in
> the directory stated in the environment variable `FREGE_LS_SOURCE_DIR`. 

> Note 2: The Frege Language Server uses the file system to resolve modules.
> Therefore, always use the file path relative to `FREGE_LS_SOURCE_DIR` as
> module name.

## Setup

You need Java & Gradle to compile and run the code.

We tested it with:

- Gradle 8.10
- Java 11.0.27

### Frege Gradle Plugin

The language server depends on the [Frege Gradle Plugin](https://github.com/tricktron/frege-gradle-plugin/tree/master). Follow the 
simple steps described in its [installation section](https://github.com/tricktron/frege-gradle-plugin/tree/master?tab=readme-ov-file#installation) to set the plugin up.

Then change into the directory of this repo and run `gradle setupFrege` to
download the Frege version specified in the `build.gradle` file.

Now you are good to go.

## Building

Once you have set the Frege Gradle plugin up, you are ready to start
developing. 

### Compile the code

Use the `gradle compileFrege` task to compile the code.

### Running tests

Use `gradle testFrege` to run the automated tests using Frege QuickCheck. 

### Creating an executable

Run `gradle installDist` to create an executable and run
`./build/install/frege-ls/bin/frege-ls` to execute the language server.

This will run [Main.fr](./src/main/frege/ch/fhnw/fregels/Main.fr) which reads messages adhering to the 
[LSP specification](https://microsoft.github.io/language-server-protocol/specifications/lsp/3.17/specification/) from standard input, evaluates them and prints the 
result to standard output.

## Developing

### Integrating with an IDE

Of course you can integrate this language server with any IDE which supports the
language server protocol. However, since [Neovim](https://github.com/neovim/neovim) v0.11 offers very easy setup 
and integration of language servers we advise to use it to try out your newly
developed features. We only need two commands to add a server to Neovim: 

- [vim.lsp.config()](https://neovim.io/doc/user/lsp.html#vim.lsp.config())
- [vim.lsp.enable()](https://neovim.io/doc/user/lsp.html#vim.lsp.enable())

To make use of those, just add the following snippet to your Neovim
configuration (and don't forget to replace `<path-to-this-repo>` in the
`cmd`-table!):

```lua
vim.lsp.config('fregels', {
  cmd = { "sh", "<path-to-the-this-repo>" .. "build/install/frege-ls/bin/frege-lsp" },
  filetypes = {'lua'},
  root_markers = { "build.gradle", "Makefile" },
})
vim.lsp.enable('fregels')
```

### Configuration

By default the language server looks for code in the `src/main/frege` directory.
If you want to change this behaviour, set the environment variable
`FREGE_LS_SOURCE_DIR` to the desired directory and restart the language server
or editor.

### Adding tests

By convention we put test cases in a separate module/file next to the code it
tests. We name the module the same as the module it tests but append `Spec` to
its name. See some already existing examples to see how we approach testing.

> If you create a new `*Spec.fr` file, don't forget to mention it in the
> `build.gradle` file in the section `frege.testModules`. As Gradle won't run it
> otherwise.

### Logging

Since standard out is used to communicate with the client, we can't use it for
debugging or logging. We therefore use a dedicated logging mechanism.
To see the logs, use `tail -f .fregels/fregels.log`.

### Creating a new release

1. Increase the version number in [Initialize.fr](./src/main/frege/ch/fhnw/fregels/messages/initialize/Initialize.fr) 
2. Create a commit 
3. Run `gradle -Pversion=vX.X.X distTar`
4. Create a new release tag in GitHub and upload the created tar

## Acknowledgements

This code builds on few ideas taken from the already existing [frege-lsp-server](https://github.com/tricktron/frege-lsp-server/tree/main). 
It does not only take this ideas further, but also adds more features and is now
completely written in Frege.
