require 'diffy'
require 'term/ansicolor'
include Term::ANSIColor

aufgaben = {'01' => 'AsciiShop', '02' => 'BarPlot', '03' => 'AsciiShop', '04' => 'AsciiShop', '05' => 'AsciiShop', '06' => 'AsciiShop', '07' => 'AsciiShop', '08' => 'AsciiShop', '09' => 'AsciiShop', '10' => 'AsciiShop'}
verbose(false)
namespace :aufgabe do
  aufgaben.each do |number, programm|
    desc "compile ##{number}, run with given input and diff against expected output"
    task "#{number}" do
      input = FileList.new("asciishop-A#{number}/Tests/*.i*")
      output = FileList.new("asciishop-A#{number}/Tests/*.o[0-9]")
      print yellow("Compiling program ##{number}"), "\n"
      sh "javac asciishop-A#{number}/*.java"
      print yellow("Testing program ##{number}"), "\n"
      input.each_with_index do |file, n|
        sh "java -Duser.language=en -Duser.country=US -cp 'asciishop-A#{number}' #{programm} < #{file} > #{file.ext("out#{n+1}")}"
      end
      output.each_with_index do |file, n|
        diff = Diffy::Diff.new(file, file.ext("out#{n+1}"), :source => 'files')
        if diff.to_s.empty?
          print green("Program ##{number} - Test ##{n+1} succeeded"), "\n"
        else
          puts diff
          print red("Program ##{number} - Test ##{n+1} failed"), "\n"
        end
      end
    end
  end
end

task :default => Rake::Task.tasks
