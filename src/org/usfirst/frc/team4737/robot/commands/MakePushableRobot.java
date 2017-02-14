package org.usfirst.frc.team4737.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4737.robot.Robot;

/**
 * @author brian
 * @version Feb. 14, 2017
 */
public class MakePushableRobot extends Command {

    public MakePushableRobot() {
        requires(Robot.DRIVE);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.DRIVE.setBrakeMode(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.DRIVE.setBrakeMode(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

}
