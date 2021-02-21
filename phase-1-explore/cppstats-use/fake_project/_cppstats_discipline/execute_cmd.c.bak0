/*
 * Project: BASH
 * File: execute_cmd.c
 *
 * Syntax Error ID = 2
 *
 * Problem: !COND_COMMAND && (DPAREN_ARITHMETIC || ARITH_FOR_COMMAND)
 *
 * Solution: We have not found solution until version 4.2 (latest version).
 *
 * How was it introduced? Modifying code and adding directives in version 3.0.
 *
 */



/* Return the line number of the currently executing command. */
int
executing_line_number ()
{
  if (executing && showing_function_line == 0 &&
      (variable_context == 0 || interactive_shell == 0) &&
      currently_executing_command)
    {
#if defined (COND_COMMAND)
      if (currently_executing_command->type == cm_cond)
	return currently_executing_command->value.Cond->line;
#endif
#if defined (DPAREN_ARITHMETIC)
      else if (currently_executing_command->type == cm_arith)
	return currently_executing_command->value.Arith->line;
#endif
#if defined (ARITH_FOR_COMMAND)
      else if (currently_executing_command->type == cm_arith_for)
	return currently_executing_command->value.ArithFor->line;
#endif

	return line_number;
    }
  else
    return line_number;
}
